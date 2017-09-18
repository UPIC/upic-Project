package com.upic.serviceimpl;

import com.upic.SpringBootJpaTestApplication;
import com.upic.condition.PrizeCondition;
import com.upic.dto.PrizeInfo;
import com.upic.repository.PrizeRepository;
import com.upic.service.PrizeService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootJpaTestApplication.class)
public class PrizeServiceImplTest {


    @Autowired
    private PrizeService prizeService;

    @Autowired
    private PrizeRepository prizeRepository;


    @Test
    public void addPrize() throws Exception {
        PrizeInfo prize = new PrizeInfo();
        prize.setContent("小米手机");
        prize.setCreatTime(new Date());
        prize.setTitle("手机");
        prize.setPrizeName("小米6");
        prize.setRemark("awesome");
        PrizeInfo p = prizeService.addPrize(prize);
        Assert.assertEquals("小米手机",p.getContent());
    }

    @Test
    public void updatePrize() throws Exception {

        PrizeInfo prize = new PrizeInfo();
        prize.setId(1L);
        prize.setCreatTime(new Date());
        prize.setContent("华为手机");
        prize.setTitle("手机");
        prize.setPrizeName("华为meta10 Plus");
        prize.setRemark("麒麟970 处理器 超乎你的想象");
        PrizeInfo p = prizeService.updatePrize(prize);
        Assert.assertEquals("华为meta10 Plus",p.getPrizeName());
    }

    @Test
    public void searchPrizes() throws Exception {

        PrizeCondition prizeCondition = new PrizeCondition();
        prizeCondition.setPrizeName("小米6");
        PageRequest p=new PageRequest();
        Page<PrizeInfo> page=prizeService.searchPrizes(prizeCondition,p);
        System.out.println(page.getTotalElements());
        System.out.println(page.getContent());
        assertEquals(1,page.getTotalElements());

    }

    @Test
    public void getPrizeById() throws Exception {
        PrizeInfo prize = prizeService.getPrizeById(1L);
        assertEquals("手机",prize.getTitle());
    }

}