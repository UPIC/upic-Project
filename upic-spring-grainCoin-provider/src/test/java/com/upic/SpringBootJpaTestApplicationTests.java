package com.upic;

import com.upic.condition.GrainCoinLogCondition;
import com.upic.common.utils.redis.UpicRedisComponent;
import com.upic.condition.IntegralOperateLogCondition;
import com.upic.condition.PrizeCondition;
import com.upic.dto.*;
import com.upic.enums.IntegralLogStatusEnum;
import com.upic.enums.IntegralLogTypeEnum;
import com.upic.po.IntegralOperateLog;
import com.upic.service.GrainCoinLogService;
import com.upic.service.IntegralLogService;
import com.upic.service.IntegralOperateLogService;
import com.upic.service.PrizeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootJpaTestApplication.class)
public class SpringBootJpaTestApplicationTests {
    @Autowired
    private GrainCoinLogService grainCoinLogService;

    @Autowired
    private IntegralLogService integralLogService;

    @Autowired
    private IntegralOperateLogService integralOperateLogService;

    @Autowired
    private PrizeService prizeService;

    /**
     * ************************************** IntegralLog *****************************************
     */
    @Test
    public void testSaveIntegralLog() {
        for (int i = 0; i < 20; i++) {
            IntegralLogInfo integralLogInfo = new IntegralLogInfo();
            integralLogInfo.setEvent("Event" + (i + 1));
            integralLogInfo.setIntegral(Math.random() * 10);
            integralLogInfo.setStudent("Student" + i);
            integralLogInfo.setType(IntegralLogTypeEnum.VOLUNTARY_APPLICATION);
            integralLogInfo.setStatus(IntegralLogStatusEnum.PENDING_AUDIT);
            IntegralLogIdInfo integralLogIdInfo = new IntegralLogIdInfo("StudentNum" + i, "ProjectNum" + i);
            integralLogInfo.setIntegralLogId(integralLogIdInfo);
            integralLogService.saveIntegralLog(integralLogInfo);
        }
    }

    @Test
    public void testAllOperation() {
        String apartment = null;
        IntegralLogTypeEnum type = null;
        IntegralLogStatusEnum status = null;
        integralLogService.allOperation(apartment, "", status, type);
    }

    @Test
    public void testWatchIntegral() {
        double score = integralLogService.watchIntegral("StudentNum12");
        System.out.println(score);
    }

    @Test
    public void testGetUserListByProjectNum() {
        PageRequest pageRequest = new PageRequest();
        Page<IntegralLogInfo> integralLogInfoPage = integralLogService.getUserListByProjectNum("ProjectNum0", pageRequest);
        System.out.println(integralLogInfoPage.getTotalElements());
        System.out.println(integralLogInfoPage.getTotalPages());
        for (IntegralLogInfo integralLogInfo : integralLogInfoPage.getContent()) {
            System.out.println(integralLogInfo);
        }
    }

    /**
     * ************************************** Prize *****************************************
     */
    @Test
    public void testAddPrize() {
        for (int i = 0; i < 20; i++) {
            PrizeInfo prizeInfo = new PrizeInfo();
            prizeInfo.setPrizeName("PrizeName" + (i + 1));
            prizeInfo.setScore(i + 1);
            prizeInfo.setTitle("Title" + (i + 1));
            prizeInfo.setContent("Content" + (i + 1));
            prizeInfo.setRemark("Remark" + (i + 1));
            prizeService.addPrize(prizeInfo);
        }
    }

    @Test
    public void testUpdatePrize() {
        PrizeInfo prizeInfo = prizeService.getPrizeById(1L);
        prizeInfo.setRemark("1111111111111");
        prizeService.updatePrize(prizeInfo);
    }

    @Test
    public void testSearchPrize() {
        PrizeCondition prizeCondition = new PrizeCondition();
        PageRequest pageRequest = new PageRequest();
        Page<PrizeInfo> prizeInfoPage = prizeService.searchPrizes(prizeCondition, pageRequest);
        System.out.println(prizeInfoPage.getTotalElements());
        System.out.println(prizeInfoPage.getTotalPages());
        for (PrizeInfo prizeInfo : prizeInfoPage.getContent()) {
            System.out.println(prizeInfo);
        }
    }

    /**
     * ************************************** IntegralOperateLog *****************************************
     */
    @Test
    public void testAddIntegralOperateLog() {
        for (int i = 0; i < 20; i++) {
            IntegralOperateLogInfo integralOperateLogInfo = new IntegralOperateLogInfo();
            integralOperateLogInfo.setEvent("Event" + (i + 1));
            integralOperateLogService.addIntegralOperateLog(integralOperateLogInfo);
        }
    }

    @Test
    public void testSearchIntegralOperateLog() {
        IntegralOperateLogCondition integralOperateLogCondition = new IntegralOperateLogCondition();
        PageRequest pageRequest = new PageRequest();
        Page<IntegralOperateLogInfo> integralOperateLogInfoPage = integralOperateLogService.searchIntegralOperateLog(integralOperateLogCondition, pageRequest);
        System.out.println(integralOperateLogInfoPage.getTotalElements());
        System.out.println(integralOperateLogInfoPage.getTotalPages());
        for (IntegralOperateLogInfo integralOperateLogInfo : integralOperateLogInfoPage.getContent()) {
            System.out.println(integralOperateLogInfo);
        }
    }

    @Test
    public void testGetByIntegralOperateLogId() {
        IntegralOperateLogInfo integralOperateLogInfo = integralOperateLogService.getByIntegralOperateLogId(1L);
        System.out.println(integralOperateLogInfo.toString());
    }

    /**
     * ************************************** GrainCoinLog *****************************************
     */
//    @Test
//    public void testExchangePrize() {
//        for (long i = 4L; i < 7L; i++) {
//            long prizeId = i;
//            GrainCoinLogInfo grainCoinLogInfo = new GrainCoinLogInfo();
//            grainCoinLogInfo.setEvent("Event" + (i - 3));
//            grainCoinLogService.exchangePrize(prizeId, grainCoinLogInfo);
//        }
//    }
    @Test
    public void testWatchGrainCoin() {
        String studentNum = "1";
        double score = grainCoinLogService.watchGrainCoin(studentNum);
        System.out.println(score);
    }

    @Test
    public void testGetAllIntegralLogByStudentNum() {
        String studentNum = "StudentNum2";
        PageRequest pageRequest = new PageRequest();
        Page<IntegralLogInfo> integralLogInfoPage = integralLogService.getAllIntegralLogByStudentNum(studentNum, pageRequest);
        System.out.println(integralLogInfoPage.getTotalElements());
        System.out.println(integralLogInfoPage.getTotalPages());
        for (IntegralLogInfo integralLogInfo : integralLogInfoPage.getContent()) {
            System.out.println(integralLogInfo);
        }
    }

    @Test
    public void testIntegralLogSearchBar() {
        String status = "ALREADY_SIGN_UP";
        String keyword = "1";
        PageRequest pageRequest = new PageRequest();
        Page<IntegralLogInfo> integralLogInfoPage = integralLogService.integralLogSearchBar(status, keyword, pageRequest);
        System.out.println(integralLogInfoPage);
    }

    @Test
    public void testUpdateIntegralLogStatus() {
        List<IntegralLogIdInfo> integralLogIdInfoList = new ArrayList<IntegralLogIdInfo>();
        IntegralLogIdInfo integralLogIdInfoOne = new IntegralLogIdInfo();
        IntegralLogIdInfo integralLogIdInfoTwo = new IntegralLogIdInfo();
        integralLogIdInfoOne.setProjectNum("PROJECT001");
        integralLogIdInfoOne.setStudentNum("1522110240");
        integralLogIdInfoTwo.setProjectNum("PROJECT027");
        integralLogIdInfoTwo.setStudentNum("1522110240");
        integralLogIdInfoList.add(integralLogIdInfoOne);
        integralLogIdInfoList.add(integralLogIdInfoTwo);

        IntegralLogStatusEnum status = IntegralLogStatusEnum.ALREADY_SIGN_UP;

        System.out.println(integralLogService.updateIntegralLogStatus(integralLogIdInfoList, status));
    }

    @Test
    public void testGetGrainCoinLog() {
        GrainCoinLogCondition grainCoinLogCondition = new GrainCoinLogCondition();
        PageRequest pageRequest = new PageRequest();
        Page<GrainCoinLogInfo> grainCoinLogInfoPage = grainCoinLogService.searchPrizeByCondition(grainCoinLogCondition, pageRequest);
        System.out.println(grainCoinLogInfoPage.getTotalElements());
        System.out.println(grainCoinLogInfoPage.getTotalPages());
        for (GrainCoinLogInfo grainCoinLogInfo : grainCoinLogInfoPage.getContent()) {
            System.out.println(grainCoinLogInfo);
        }
    }
}
