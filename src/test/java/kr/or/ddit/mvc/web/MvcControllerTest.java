package kr.or.ddit.mvc.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mock.web.MockMultipartFile;

import kr.or.ddit.test.config.WebTestConfig;

public class MvcControllerTest extends WebTestConfig{

	@Test
	public void fileuploadViewTest() throws Exception{
		mockMvc.perform(get("/mvc/fileupload/view"))
				.andExpect(status().isOk())
				.andExpect(view().name("file/view"))
				.andDo(print())
				.andReturn();
	}
	
	@Test
	public void fileuploadTest() throws Exception{
		//String name, String originalfilename, String contentType, InputStream inputStream
		ClassPathResource resource = new ClassPathResource("kr/or/ddit/upload/brown.png");
		MockMultipartFile file = new MockMultipartFile("picture", "brown.png", "image/png", resource.getInputStream());
		
		mockMvc.perform(fileUpload("/mvc/fileupload/upload")
				.file(file)
				.param("userid", "brown"))
				.andExpect(view().name("file/view"))
				.andDo(print());
		
	}

}
