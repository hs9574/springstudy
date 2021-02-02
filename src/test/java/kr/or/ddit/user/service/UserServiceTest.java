package kr.or.ddit.user.service;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.test.config.ModelTestConfig;
import kr.or.ddit.user.model.UserVo;

public class UserServiceTest extends ModelTestConfig{

	@Resource(name="userService")
	private UserService userService;
	
	@Before
	public void setup() {
		
		//테스트에서 사용할 신규 사용자 추가
		UserVo userVo = new UserVo("testUser", "테스트사용자", "testUserPass", new Date(), "대덕", "대전 중구 중앙로 76", "4층", "34940", "brown.png", "uuid-generated-filename.png");
		
		userService.registUser(userVo);
		
		//신규 입력 테스트를 위해 테스트 과정에서 입력된 데이터를 삭제
		userService.deleteUser("ddit_n");
	}
	
	@After
	public void tearDown() {
		userService.deleteUser("testUser");
	}
	
	@Test
	public void getUserTest() {
		/***Given***/
		String userid = "brown";

		/***When***/
		UserVo userVo = userService.selectUser(userid);
		
		/***Then***/
		assertEquals("브라운", userVo.getUsernm());
	}

	@Test
	public void selectAllUsertest() {
		/*** Given ***/

		/*** When ***/
		List<UserVo> userList = userService.selectAllUser();

		/*** Then ***/
		assertEquals(19, userList.size());
	}
	
	
	@Test
	public void selectUsertest() {
		/***Given***/
		String userid = "brown";

		/***When***/
		UserVo user = userService.selectUser(userid);
		/***Then***/
		assertNotNull(user);
		assertEquals("브라운", user.getUsernm());
	}
	
	@Test
	public void selectUserNotExsistTest() {
		/***Given***/
		String userid = "NOTbrown";

		/***When***/
		UserVo user = userService.selectUser(userid);
		/***Then***/
		assertNull(user);
	}
	
	@Test
	public void selectPagingUsertest() {
		/***Given***/
		
		PageVo pvo = new PageVo();
		pvo.setPage(2);
		pvo.setPagesize(5);

		/***When***/
		//List<UserVo> userlist = userService.selectPagingUser(pvo);
		Map<String, Object> map = userService.selectPagingUser(pvo);
		List<UserVo> userList = (List<UserVo>)map.get("userList");
		/***Then***/
		assertEquals(5, userList.size());
	}
	
	@Test
	public void modifyUserTest() {
		/***Given***/
		//userid, usernm, pass, reg_Dt, alias, addr1, addr2, zipcode
		UserVo userVo = new UserVo("ddit", "대덕인재", "dditPass", new Date(), "개발원_S", "대전시 중구 중앙로 76", "4층 대덕인재개발원", "34940", "brown.png", "uuid-generated-filename.png");
		
		/***When***/
		int updateCnt = userService.modifyUser(userVo);
		
		/***Then***/
		assertEquals(1, updateCnt);
	}
	
	@Test
	public void registUserTest() {
		/***Given***/
		//userid, usernm, pass, reg_Dt, alias, addr1, addr2, zipcode
		UserVo userVo = new UserVo("ddit_n", "대덕인재", "dditpass", new Date(), "개발원_m", "대전시 중구 중앙로 76", "4층 대덕인재개발원", "34940", "brown.png", "uuid-generated-filename.png");
		
		/***When***/
		int registCnt = userService.registUser(userVo);
		
		/***Then***/
		assertEquals(1, registCnt);
	}
	
	@Test
	public void deleteUserTest() {
		/***Given***/
		//해당 테스트가 실행될 때는 testUser라는 사용자가 before 메소드에 의해 등록이 된 상태
		String userid = "testUser";
		/***When***/
		int deleteCnt = userService.deleteUser(userid);
		/***Then***/
		assertEquals(1, deleteCnt);
	}
	

}
