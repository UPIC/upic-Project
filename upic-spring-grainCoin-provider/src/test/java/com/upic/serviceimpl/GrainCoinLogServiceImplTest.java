package com.upic.serviceimpl;

import com.upic.SpringBootJpaTestApplication;
import com.upic.common.base.enums.JugeType;
import com.upic.common.base.enums.JygeTypeEnum;
import com.upic.condition.GrainCoinLogCondition;
import com.upic.condition.IntegralLogCondition;
import com.upic.dto.GrainCoinLogInfo;
import com.upic.dto.IntegralLogIdInfo;
import com.upic.dto.IntegralLogInfo;
import com.upic.service.GrainCoinLogService;
import com.upic.service.IntegralLogService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootJpaTestApplication.class)
public class GrainCoinLogServiceImplTest {

    @Autowired
    private GrainCoinLogService grainCoinLogService;
    @Autowired
    private IntegralLogService integralLogService;

    @Test
    public void watchGrainCoin() throws Exception {
        String userNum = "";
    }

    @Test
    public void searchPrizeByCondition() throws Exception {
    	IntegralLogCondition g=new IntegralLogCondition();
    	List<Map<String, Object>> orList =new ArrayList<Map<String, Object>>();
    	Map<String, Object> map1=new IdentityHashMap<String, Object>();
    	
    	map1.put(new String("clazz"), new JugeType(JygeTypeEnum.LIKE, "14"));
    	map1.put(new String("clazz"), new JugeType(JygeTypeEnum.EQUAL, "15微社交1"));
//    	Map<String, Object> map2=new IdentityHashMap<String, Object>();
//    	map2.put(new String("projectName"), "新苗");
//    	map2.put(new String("projectName"), "社会实践活动010");
    	orList.add(map1);
//    	orList.add(map2);
    	g.setOrList(orList);
    	Page<IntegralLogInfo> searchIntegralLog = integralLogService.searchIntegralLog(g, new PageRequest(0, 20));
    	searchIntegralLog.getContent().forEach(System.out::println);
    }

    @Test
    public void exchangePrize() throws Exception {
    }
    
}
