package kr.or.ddit.ioc;

import javax.annotation.Resource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.test.config.WebTestConfig;
import kr.or.ddit.user.model.UserVo;


public class TypeConversionTest extends WebTestConfig{
	private static final Logger logger = LoggerFactory.getLogger(TypeConversionTest.class);
	
	@Resource(name="user")
	private UserVo user;
	
	@Test
	public void userTest() {
		logger.debug("user.getReg_dt() : {}", user.getReg_dt());
		logger.debug("user.getHire_dt() : {}", user.getHire_dt());
		logger.debug("user.getPrice() : {}", user.getPrice());
	}

}
