package kr.or.ddit.user.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.UserService;
import kr.or.ddit.util.FileUtil;
import kr.or.ddit.validator.UserVoValidator;

@RequestMapping("user")
@Controller
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Resource(name = "userService")
	private UserService userService;
	
	@RequestMapping("allUser")
	public String allUser(Model model) {
		model.addAttribute("allUserList", userService.selectAllUser());
		return "user/allUser";
	}
	
	@RequestMapping("allUserTiles")
	public String allUserTiles(Model model) {
		model.addAttribute("allUserList", userService.selectAllUser());
		return "tiles.user.allUser";
	}
	
	@RequestMapping("pagingUser")
	public String pagingUser(@RequestParam(defaultValue = "1") int page,@RequestParam(defaultValue = "5") int pageSize, Model model) {
		
		PageVo pagevo = new PageVo(page, pageSize);
		
		model.addAllAttributes(userService.selectPagingUser(pagevo));
		
		return "user/pagingUser";
	}
	
	@RequestMapping("pagingUserTiles")
	public String pagingUserTiles(@RequestParam(defaultValue = "1") int page,@RequestParam(defaultValue = "5") int pageSize, Model model) {
		
		PageVo pagevo = new PageVo(page, pageSize);
		
		model.addAllAttributes(userService.selectPagingUser(pagevo));
		
		//tiles-definition에서 설정한 name
		return "tiles.user.pagingUser";
	}
	
	//사용자 리스트가 없는 상태의 화면만 응답으로 생성
	@RequestMapping("pagingUserAjaxView")
	public String pagingUserAjaxView() {
	
		return "tiles.user.pagingUserAjax";
	}
	
	@RequestMapping("pagingUserAjax")
	public String pagingUserAjax(@RequestParam(defaultValue = "1") int page,@RequestParam(defaultValue = "5") int pageSize, Model model) {
		
		PageVo pagevo = new PageVo(page, pageSize);
		
		model.addAllAttributes(userService.selectPagingUser(pagevo));
	
		return "jsonView";
	}
	
	@RequestMapping("pagingUserAjaxHtml")
	public String pagingUserAjaxHtml(@RequestParam(defaultValue = "1") int page,@RequestParam(defaultValue = "5") int pageSize, Model model) {
		
		PageVo pagevo = new PageVo(page, pageSize);
		
		model.addAllAttributes(userService.selectPagingUser(pagevo));
	
		return "user/pagingUserAjaxHtml";
		
		/*
		 * pagingUserAjaxHtml ==> /WEB-INF/views/user/pagingUserAjaxHtml.jsp 
		 */
	}
	
//	@RequestMapping("pagingUser")
//	public String pagingUser(PageVo pageVo) {
//		logger.debug("pageVo : {}", pageVo);
//		
//		return "";
//	}
	
	@RequestMapping("user")
	public String userDetail(String userid, Model model) {
		model.addAttribute("user", userService.selectUser(userid));
		return "user/user";
	}
	
	@RequestMapping(path="userModify", method = RequestMethod.POST)
	public String userModify(Model model, MultipartFile profile, UserVo userVo) {
		
		String filename = profile.getOriginalFilename();
		String fileExtension = FileUtil.getFileExtension(filename);
		String realfilename = UUID.randomUUID().toString()+fileExtension;
		userVo.setFilename(filename);
		userVo.setRealfilename(realfilename);
		try {
			profile.transferTo(new File("d:\\upload\\"+realfilename));
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		
		int updateCnt = userService.modifyUser(userVo);
		
		model.addAttribute("user", userService.selectUser(userVo.getUserid()));
		if(updateCnt == 1) {
			return "redirect:/user/user?userid="+userVo.getUserid();
		}else {
			return "user/userModify";
		}
	}
	
	@RequestMapping(path="userModify", method = RequestMethod.GET)
	public String userModify(Model model, String userid) {
		model.addAttribute("user", userService.selectUser(userid));
		return "user/userModify";
	}
	
	@RequestMapping(path="registUser", method = RequestMethod.GET)
	public String registUser() {
		return "user/registUser";
	}
	
	//bindingResult 객체는 command 객체 바로 뒤에 인자로 기술해야 한다.
	@RequestMapping(path="registUser", method = RequestMethod.POST)
	public String registUser(Model model, MultipartFile profile, @Valid UserVo userVo, BindingResult result) {
		
//		new UserVoValidator().validate(userVo, result);
		
		if(result.hasErrors()) {
			logger.debug("result has error");
			return "user/registUser";
		}
		
		if(profile.getSize() > 0) {
			String filename = profile.getOriginalFilename();
			String fileExtension = FileUtil.getFileExtension(filename);
			String realfilename = UUID.randomUUID().toString()+fileExtension;
			userVo.setFilename(filename);
			userVo.setRealfilename(realfilename);
			
			try {
				profile.transferTo(new File("d:\\upload\\"+realfilename));
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			} 
		}
		
		int insertCnt = 0; 
				
		try {
			insertCnt = userService.registUser(userVo);
		} catch (Exception e) {
			insertCnt = 0;
		}
		
		if(insertCnt == 1) {
			return "redirect:/user/pagingUser";
		}else {
			return "user/registUser";
		}
	}
	
	@RequestMapping("deleteUser")
	public String deleteUser(String userid) {
		int deleteCnt = 0;
		
		try {
			deleteCnt = userService.deleteUser(userid);
		} catch (Exception e) {
			deleteCnt = 0;
		}
		
		if(deleteCnt == 1) {
			return "redirect:/user/pagingUser";
		}else {
			return "redirect:/user/user";
		}
	}
	
	@RequestMapping("excelDownload")
	public String excelDownload(Model model) {
		List<String> header = new ArrayList<>();
		header.add("사용자 아이디");
		header.add("사용자 이름");
		header.add("사용자 별명");
		
		model.addAttribute("header", header);
		model.addAttribute("data", userService.selectAllUser());
		
		return "userExcelDownloadView";
	}
	
	@RequestMapping("profile")
	public void profile(HttpServletResponse resp, HttpServletRequest req, String userid) {
		resp.setContentType("image");
		
		UserVo userVo = userService.selectUser(userid);
		
		String path = "";
		if(userVo.getRealfilename() == null) {
			path = req.getServletContext().getRealPath("/image/unknown.png");
		}else {
			path = userVo.getRealfilename();
		}
		
		logger.debug("path : {}", path);
		
		try {
			FileInputStream fis = new FileInputStream(path);
			
			ServletOutputStream sos = resp.getOutputStream();
			byte[] buff = new byte[512];
			while(fis.read(buff) != -1) {
				sos.write(buff);
			}
			fis.close();
			sos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("profileDownload")
	public String profileDownload(String userid, Model model) {
		
		UserVo userVo = userService.selectUser(userid);
		
		model.addAttribute("realFilename", userVo.getRealfilename());
		model.addAttribute("filename", userVo.getFilename());
		
		return "fd";
	}
}
