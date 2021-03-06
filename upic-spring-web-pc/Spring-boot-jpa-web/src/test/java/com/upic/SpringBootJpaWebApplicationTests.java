package com.upic;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import javax.servlet.http.Cookie;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootJpaWebApplication.class)
public class SpringBootJpaWebApplicationTests {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void start() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void contextLoads() throws Exception {
		String contentAsString = mockMvc
				.perform(get("/stu").accept(MediaType.APPLICATION_JSON_UTF8).param("name", "董帅").cookie(new Cookie("cookie", "I am cookie")).header("auth", "xxxx"))
				.andExpect(status().isOk()).andExpect(jsonPath("$.length()").value(3)).andReturn().getResponse()
				.getContentAsString();
		System.out.println(contentAsString);
	}

	@Test
	public void getPage() throws Exception {
		String contentAsString = mockMvc
				.perform(get("/stu/page").accept(MediaType.APPLICATION_JSON_UTF8).param("name", "董帅").param("page", "1")
						.param("size", "10").param("sort", "name,DESC"))
				.andExpect(status().isOk()).andExpect(jsonPath("$.stuNum").value("1422110108")).andReturn()
				.getResponse().getContentAsString();
		System.out.println(contentAsString);
	}

	@Test
	public void test() throws Exception {

		String contentAsString = mockMvc
				.perform(get("/stu/page").accept(MediaType.APPLICATION_JSON_UTF8).param("name", "董帅").param("id", "10")
						.param("page", "1").param("size", "10").param("sort", "name,DESC"))
				.andExpect(status().is4xxClientError()).andReturn().getResponse().getContentAsString();
		System.out.println(contentAsString);
	}

	@Test
	public void testPost() throws Exception {
		String content = "{\"id\":null,\"stuNum\":null,\"password\":null,\"classNum\":\"战争与和平\", \"createTime\":"
				+ new Date().getTime() + "}";
		String contentAsString = mockMvc
				.perform(post("/stu").content(content).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		System.out.println(contentAsString);
	}

	@Test
	public void deleteTest() throws Exception {
		mockMvc.perform(delete("/stu/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}
	@Test
	public void testPut() throws Exception {
		String content = "{\"id\":null,\"stuNum\":null,\"password\":null,\"classNum\":\"战争与和平\", \"createTime\":"
				+ new Date().getTime() + "}";
		String contentAsString = mockMvc
				.perform(put("/stu/1").content(content).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		System.out.println(contentAsString);
	}
	
	@Test
	public void testUpload() throws Exception {
		String contentAsString = mockMvc
				.perform(fileUpload("/file/upload").file(new MockMultipartFile(
						"file", "a.txt", "multipart/form-data", "hello upload".getBytes("UTF-8")))
						.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		System.out.println(contentAsString);
	}
}
