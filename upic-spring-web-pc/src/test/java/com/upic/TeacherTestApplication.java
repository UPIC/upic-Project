package com.upic;

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

import javax.servlet.http.Cookie;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootJpaWebApplication.class)
public class TeacherTestApplication {

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

    @Test
    public void testGetIntegeral() throws Exception {

        String contentAsString = mockMvc
                .perform(get("/stu/getIntegeral").accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
//    	String contentAsString = mockMvc
//                .perform(get("/stu/getIntegeral").accept(MediaType.APPLICATION_JSON_UTF8))
//                .andExpect(status().is5xxServerError()).andReturn().getResponse().getContentAsString();
        System.out.println("数据:" + contentAsString);
    }

    @Test
    public void testGetCrainCoin() throws Exception {

        String contentAsString = mockMvc
                .perform(get("/stu/getCrainCoin").accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
//    	String contentAsString = mockMvc
//                .perform(get("/stu/getIntegeral").accept(MediaType.APPLICATION_JSON_UTF8))
//                .andExpect(status().is5xxServerError()).andReturn().getResponse().getContentAsString();
        System.out.println("数据:" + contentAsString);
    }

    @Test
    public void getProjectByProjectNum() throws Exception {
        String contentAsString = mockMvc
                .perform(get("/teacher/getProjectByProjectNum").accept(MediaType.APPLICATION_JSON_UTF8).param("projectNum", "1"))
                .andExpect(status().isOk()).andReturn().getResponse()
                .getContentAsString();
        System.out.println(contentAsString);
    }

    @Test
    public void getProjectByUser() throws Exception {
        String contentAsString = mockMvc
                .perform(get("/teacher/getProjectByGuidanceNum").accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk()).andReturn().getResponse()
                .getContentAsString();
        System.out.println(contentAsString);
    }

    @Test
    public void getUserListByProjectNum() throws Exception {
        String contentAsString = mockMvc
                .perform(get("/teacher/getUserListByProjectNum").accept(MediaType.APPLICATION_JSON_UTF8).param("projectNum", "ProjectNum0"))
                .andExpect(status().isOk()).andReturn().getResponse()
                .getContentAsString();
        System.out.println(contentAsString);
    }

    @Test
    public  void gettest(){
        System.out.println(null!=null);
    }
}
