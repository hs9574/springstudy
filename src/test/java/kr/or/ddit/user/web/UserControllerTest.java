package kr.or.ddit.user.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;

import kr.or.ddit.test.config.WebTestConfig;


public class UserControllerTest extends WebTestConfig{

	@Test
	public void selectAllUserTest() throws Exception {
		mockMvc.perform(get("/user/allUser"))
				.andExpect(status().isOk())
				.andExpect(view().name("user/allUser"))
				.andExpect(model().attributeExists("allUserList"))
				.andDo(print());
	}
	
	@Test
	public void pagingUserTest() throws Exception {
		mockMvc.perform(get("/user/pagingUser"))
		.andExpect(status().isOk())
		.andExpect(view().name("user/pagingUser"))
		.andExpect(model().attributeExists("userList"))
		.andExpect(model().attributeExists("pageVo"))
		.andExpect(model().attributeExists("pagination"))
		.andDo(print());
	}
	
	@Test
	public void pagingUser2Test() throws Exception {
		mockMvc.perform(get("/user/pagingUser").param("page", "2"))
		.andExpect(status().isOk())
		.andExpect(view().name("user/pagingUser"))
		.andExpect(model().attributeExists("userList"))
		.andExpect(model().attributeExists("pageVo"))
		.andExpect(model().attributeExists("pagination"))
		.andDo(print());
	}
	
	@Test
	public void userDetailTest() throws Exception{
		mockMvc.perform(get("/user/user").param("userid", "brown"))
					.andExpect(status().isOk())
					.andExpect(view().name("user/user"))
					.andExpect(model().attributeExists("user"))
					.andDo(print());
	}
	
	@Test
	public void userModifyGetTest() throws Exception{
		mockMvc.perform(get("/user/userModify").param("userid", "brown"))
					.andExpect(status().isOk())
					.andExpect(view().name("user/userModify"))
					.andExpect(model().attributeExists("user"))
					.andDo(print());
	}
	
	
}
