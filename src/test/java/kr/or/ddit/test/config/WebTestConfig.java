package kr.or.ddit.test.config;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ContextConfiguration( locations = {"classpath:/kr/or/ddit/config/spring/application-context.xml" , 
									"classpath:/kr/or/ddit/config/spring/root-context.xml",
									"classpath:/kr/or/ddit/config/spring/datasource-context.xml",
									"classpath:/kr/or/ddit/ioc/component-scan.xml",
									"classpath:/kr/or/ddit/ioc/typeConversion.xml",
									"classpath:/kr/or/ddit/ioc/ioc.xml"})
@WebAppConfiguration //스프링 환경을 WEB 기반의 application Context 로 생성 
@RunWith(SpringJUnit4ClassRunner.class)
public class WebTestConfig {
   
   
   @Autowired 
   private WebApplicationContext context; 
   
   protected MockMvc mockMvc; 

   
   //mockupmvc객체 를 만들기 위해서. 모든 테스트에 적용하기 위해서. 
   
   @Before
   public void setup() {
      //mockMvc 객체가 만들어진다. 
      mockMvc = MockMvcBuilders.webAppContextSetup(context).build(); 
      //매번 mockMvc 가 만들어진다. 
   }
   
   @Ignore
   @Test
	public void dummy() {
		
	}
   

}