package kr.or.ddit.user.repository;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

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

//eclipse / maven에는 junit test를 내장
//스프링 환경에서 junit 코드를 실행 ==> junit 코드도 스프링 빈으로 등록 

public class UserDaoTest extends ModelTestConfig{
	
	@Resource(name="userDao")
	private UserDao userDao;
	
	@Before
	public void setup() {
		
		//테스트에서 사용할 신규 사용자 추가
		UserVo userVo = new UserVo("testUser", "테스트사용자", "testUserPass", new Date(), "대덕", "대전 중구 중앙로 76", "4층", "34940", "brown.png", "uuid-generated-filename.png");
		
		userDao.registUser(userVo);
		
		//신규 입력 테스트를 위해 테스트 과정에서 입력된 데이터를 삭제
		userDao.deleteUser("ddit_n");
	}
	
	@After
	public void tearDown() {
		userDao.deleteUser("testUser");
	}
	
	@Test
	public void getUserTest() {
		/***Given***/
		String userid = "brown";

		/***When***/
		UserVo userVo = userDao.selectUser(userid);
		
		/***Then***/
		assertEquals("브라운", userVo.getUsernm());
	}
	
	@Test
	public void selectAllUsertest() {
		/*** Given ***/

		/*** When ***/
		List<UserVo> userList = userDao.selectAllUser();

		/*** Then ***/
		assertEquals(19, userList.size());
	}
	
	@Test
	public void selectPagingUsertest() {
		/***Given***/
		
		PageVo pvo = new PageVo();
		pvo.setPage(2);
		pvo.setPagesize(5);

		/***When***/
		List<UserVo> userlist = userDao.selectPagingUser(pvo);
		/***Then***/
		assertEquals(5, userlist.size());
	}
	
	@Test
	public void selectAllUserCntTest() {
		/***Given***/

		/***When***/
		int userCnt = userDao.selectAllUserCnt();

		/***Then***/
		assertEquals(19, userCnt);
	}

	@Test
	public void modifyUserTest() {
		/***Given***/
		//userid, usernm, pass, reg_Dt, alias, addr1, addr2, zipcode
		UserVo userVo = new UserVo("ddit", "대덕인재", "dditpass", new Date(), "개발원_m", "대전시 중구 중앙로 76", "4층 대덕인재개발원", "34940", "brown.png", "uuid-generated-filename.png");
		
		/***When***/
		int updateCnt = userDao.modifyUser(userVo);
		
		/***Then***/
		assertEquals(1, updateCnt);
	}
	
	@Test
	public void registUserTest() {
		/***Given***/
		//userid, usernm, pass, reg_Dt, alias, addr1, addr2, zipcode
		UserVo userVo = new UserVo("ddit_n", "대덕인재", "dditpass", new Date(), "개발원_m", "대전시 중구 중앙로 76", "4층 대덕인재개발원", "34940", "brown.png", "uuid-generated-filename.png");
		
		/***When***/
		int registCnt = userDao.registUser(userVo);
		
		/***Then***/
		assertEquals(1, registCnt);
	}
	
	@Test
	public void deleteUserTest() {
		/***Given***/
		//해당 테스트가 실행될 때는 testUser라는 사용자가 before 메소드에 의해 등록이 된 상태
		String userid = "testUser";
		/***When***/
		int deleteCnt = userDao.deleteUser(userid);
		/***Then***/
		assertEquals(1, deleteCnt);
	}

}
