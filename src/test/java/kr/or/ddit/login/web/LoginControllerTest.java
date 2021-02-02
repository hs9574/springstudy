package kr.or.ddit.login.web;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.test.config.WebTestConfig;
import kr.or.ddit.user.model.UserVo;

public class LoginControllerTest extends WebTestConfig {

	@Test
	public void viewTest() throws Exception {
		//localhost/login/view+ enter => GET방식
		MvcResult mvcResult =mockMvc.perform(get("/hello/view"))
			.andExpect(status().isOk())
			.andExpect(view().name("hello"))
			.andExpect(model().attributeExists("userVo"))
			.andDo(print())
			.andReturn();
		    ModelAndView mav = mvcResult.getModelAndView();
		    
		    assertEquals("hello", mav.getViewName());
		    UserVo userVo = (UserVo)mav.getModel().get("userVo");
		    assertEquals("브라운", userVo.getUsernm());
	}
	
	@Test
	public void processSuccessTest() throws Exception {
		mockMvc.perform(post("/login/process").param("userid", "brown").param("pass", "brownPass").param("price", "1000"))
				.andExpect(view().name("main"))
				.andDo(print());
	}
	
	@Test
	public void processFailTest() throws Exception {
		mockMvc.perform(post("/login/process").param("userid", "brown").param("pass", "failPass").param("price", "1000"))
				.andExpect(view().name("redirect:/login/view"))
				.andDo(print());
	}

}






