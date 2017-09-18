package com.upic.serviceimpl;

import com.upic.SpringBootJpaTestApplication;
import com.upic.service.GrainCoinLogService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootJpaTestApplication.class)
public class GrainCoinLogServiceImplTest {

    @Autowired
    private GrainCoinLogService grainCoinLogService;


    @Test
    public void watchGrainCoin() throws Exception {
        String userNum = "";
    }

    @Test
    public void searchPrizeByCondition() throws Exception {
    }

    @Test
    public void exchangePrize() throws Exception {
    }

}