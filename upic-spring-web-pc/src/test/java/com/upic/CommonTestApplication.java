package com.upic;

import com.upic.dto.IntegralLogIdInfo;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootJpaWebApplication.class)
public class CommonTestApplication {
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void start() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void getUserListByProjectNum() throws Exception {
        String contentAsString = mockMvc
                .perform(get("/common/integralLogSearchBar").accept(MediaType.APPLICATION_JSON_UTF8).param("keyword", "1"))
                .andExpect(status().isOk()).andReturn().getResponse()
                .getContentAsString();
        System.out.println(contentAsString);
    }

    @Test
    public void updateIntegralLogStatus() throws Exception {
        String contentAsString = mockMvc
                .perform(get("/common/updateIntegralLogStatus").accept(MediaType.APPLICATION_JSON_UTF8).param("integralLogIdInfos", "").param("status", "ALREADY_SIGN_UP"))
                .andExpect(status().isOk()).andReturn().getResponse()
                .getContentAsString();
        System.out.println(contentAsString);
    }

    @Test
    public void t() {
        IntegralLogIdInfo integralLogIdInfoOne = new IntegralLogIdInfo();
        IntegralLogIdInfo integralLogIdInfoTwo = new IntegralLogIdInfo();

        integralLogIdInfoOne.setProjectNum("PROJECT027");
        integralLogIdInfoOne.setStudentNum("1522110240");

        integralLogIdInfoTwo.setStudentNum("1522110240");
        integralLogIdInfoTwo.setProjectNum("PROJECT001");

        List<IntegralLogIdInfo> integralLogIdInfoList = new ArrayList<>();
        integralLogIdInfoList.add(integralLogIdInfoOne);
        integralLogIdInfoList.add(integralLogIdInfoTwo);

        System.out.println(integralLogIdInfoList.toString());
    }
    
    @Test
    public void testGetProjectNum() throws Exception {
        String contentAsString = mockMvc
                .perform(get("/common/getTeacherNowWorkloadSummary").accept(MediaType.APPLICATION_JSON_UTF8).param("teacherNum", "101045"))
                .andExpect(status().isOk()).andReturn().getResponse()
                .getContentAsString();
        System.out.println(contentAsString);
    }
}
