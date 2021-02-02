package kr.or.ddit.user.web;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.UserService;
import kr.or.ddit.util.FileUtil;

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
	
	@RequestMapping("pagingUser")
	public String pagingUser(@RequestParam(defaultValue = "1") int page,@RequestParam(defaultValue = "5") int pageSize, Model model) {
		
		PageVo pagevo = new PageVo(page, pageSize);
		
		model.addAllAttributes(userService.selectPagingUser(pagevo));
		
		return "user/pagingUser";
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
	public String userModify(Model model, MultipartFile profile,String userid, UserVo userVo) {
		
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
		
		model.addAttribute("user", userService.selectUser(userid));
		if(updateCnt == 1) {
			return "redirect:/user/user?userid="+userid;
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
	
	@RequestMapping(path="registUser", method = RequestMethod.POST)
	public String registUser(Model model, MultipartFile profile, UserVo userVo) {
		
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
}
