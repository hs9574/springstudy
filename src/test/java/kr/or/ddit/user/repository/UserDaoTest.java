package kr.or.ddit.user.repository;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.user.model.UserVo;

//eclipse / maven에는 junit test를 main 메소드 없이 자체적으로 진행할 수 있다.
//스프링 환경에서 junit 코드를 실행 ==> junit 코드도 스프링 빈으로 등록
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/kr/or/ddit/ioc/ioc.xml")
public class UserDaoTest {
	
	@Resource(name="userDao")	//주입받고 시픈 이름
	private UserDao userDao;
	
	@Test
	public void getUserTest() {
		/***Given***/
		String userid = "brown";

		/***When***/
		UserVo userVo = userDao.getUser(userid);
		
		/***Then***/
		assertEquals("브라운", userVo.getUsernm());
	}

}
