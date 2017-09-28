package com.upic.controller;

import com.upic.condition.*;
import com.upic.dto.*;
import com.upic.enums.IntegralLogStatusEnum;
import com.upic.enums.IntegralLogTypeEnum;
import com.upic.service.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.Date;

@RestController
@RequestMapping("/stu")
public class StudetAllController {
    protected static final Logger LOGGER = LoggerFactory.getLogger(StudetAllController.class);

    @Autowired
    private IntegralLogService integralLogService;

    @Autowired
    private GrainCoinLogService grainCoinLogService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private BannerService bannerService;

    @Autowired
    private PrizeService prizeService;

    /**
     * 获取当前用户的积分*
     *
     * @return
     * @throws Exception
     */
    @GetMapping("/getIntegeral")
    public double getIntegeral() throws Exception {
        try {
            // Authentication authentication =
            // SecurityContextHolder.getContext().getAuthentication();
            // System.out.println(authentication);
            // SocialUser user = null;
            // if (authentication != null) {
            // user = (SocialUser) authentication.getPrincipal();
            // }
            // if(user==null){
            // throw new Exception("获取用户失败");
            // }
            return integralLogService.watchIntegral("1522110240");
        } catch (Exception e) {
            LOGGER.info("getIntegeral :" + e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    /**
     * 获取当前学生素拓币*
     *
     * @return
     * @throws Exception
     */
    @GetMapping("/getGrainCoin")
    public double getGrainCoin() throws Exception {
        try {
            // Authentication authentication =
            // SecurityContextHolder.getContext().getAuthentication();
            // System.out.println(authentication);
            // SocialUser user = null;
            // if (authentication != null) {
            // user = (SocialUser) authentication.getPrincipal();
            // }
            // if(user==null){
            // throw new Exception("获取用户失败");
            // }
            return grainCoinLogService.watchGrainCoin("1522110240");
        } catch (Exception e) {
            LOGGER.info("getGrainCoin:" + e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    /**
     * 获取banner图*
     *
     * @param pageable
     * @param b
     * @return
     * @throws Exception
     */
    @GetMapping("/getBanner")
    public Page<BannerInfo> getBanner(@PageableDefault(size = 10) Pageable pageable, BannerCondition b)
            throws Exception {
        try {
            return bannerService.searchBanner(b, pageable);
        } catch (Exception e) {
            LOGGER.info("getBanner:" + e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    /**
     * 奖品交换*
     *
     * @param prizeId
     * @return
     */
    @GetMapping("/getExchangePrize")
    public PrizeInfo getExchangePrize(Long prizeId) {
        return null;
    }

    /**
     * 查找积分列表*
     *
     * @param i
     * @param pageable
     * @return
     * @throws Exception
     */
    @GetMapping("/getPageIntegral")
    public Page<IntegralLogInfo> getPageIntegral(IntegralLogCondition i, @PageableDefault(size = 10) Pageable pageable) throws Exception {
        try {
            return integralLogService.searchIntegralLog(i, pageable);
        } catch (Exception e) {
            LOGGER.info("getProjectInfo:" + e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    /**
     * 是否已报名*（需要修改）
     *
     * @param studentNum
     * @param projectNum
     * @return
     * @throws Exception
     */
    @GetMapping("/isSignUpByIntegralLogId")
    public String isSignUpByIntegralLogId(String studentNum, String projectNum) throws Exception {
        try {
            IntegralLogIdInfo integralLogIdInfo = new IntegralLogIdInfo(studentNum, projectNum);
            IntegralLogInfo integralLogInfo = integralLogService.getByIntegralLogId(integralLogIdInfo);
            return integralLogInfo == null ? "error" : "success";
        } catch (Exception e) {
            LOGGER.info("isSignUpByIntegralLogId:" + e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    /**
     * 积分申请提交*
     *
     * @param integralLogInfo
     * @return
     */
    //@RequestParam(name = "file",required=false) MultipartFile file
    //,  MultipartHttpServletRequest reques
    @PostMapping("/postIntegralLog")
    public IntegralLogInfo postIntegralLog(IntegralLogInfo integralLogInfo) throws Exception {
        try {
            System.out.println(integralLogInfo.toString());

            IntegralLogIdInfo integralLogIdInfo = new IntegralLogIdInfo();
            integralLogIdInfo.setProjectNum("VOLUNTARY_APPLICATION" + (new Date()).getTime());
            integralLogIdInfo.setStudentNum("1522110240");

            integralLogInfo.setCollege("信息工程学院");
            integralLogInfo.setClazz("15微社交");
            integralLogInfo.setStudent("章威男");
            integralLogInfo.setStatus(IntegralLogStatusEnum.PENDING_AUDIT);
            integralLogInfo.setType(IntegralLogTypeEnum.VOLUNTARY_APPLICATION);
            integralLogInfo = integralLogService.saveIntegralLog(integralLogInfo);
            return integralLogInfo;
        } catch (Exception e) {
            LOGGER.info("postIntegralLog:" + e.getMessage());
            throw new Exception(e.getMessage());
        }
    }
    //

    /**
     * 根据自身编号获取参加过的自主申报项目（可能没用）
     *
     * @param studentNum
     * @param pageable
     * @return
     * @throws Exception
     */
    @GetMapping("/getIntegralLogByMySelf")
    public Page<IntegralLogInfo> getIntegralLogByMySelf(String studentNum, @PageableDefault(size = 10) Pageable pageable) throws Exception {
        try {
            return integralLogService.getIntegralLogByMySelf(studentNum, pageable);
        } catch (Exception e) {
            LOGGER.info("getIntegralLogByMySelf:" + e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    /**
     * 学生查看自身的积分日志（可能没用）
     *
     * @param studentNum
     * @param pageable
     * @return
     * @throws Exception
     */
    @GetMapping("/getAllIntegralLogByStudentNum")
    public Page<IntegralLogInfo> getAllIntegralLogByStudentNum(String studentNum, @PageableDefault(size = 10) Pageable pageable) throws Exception {
        try {
            return integralLogService.getAllIntegralLogByStudentNum(studentNum, pageable);
        } catch (Exception e) {
            LOGGER.info("getAllIntegralLogByStudentNum:" + e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    /**
     * 获取申报中的积分（可能没用）
     *
     * @param studentNum
     * @param pageable
     * @return
     * @throws Exception
     */
    @GetMapping("/getIntegralLogDeclaring")
    public Page<IntegralLogInfo> getIntegralLogDeclaring(String studentNum, @PageableDefault(size = 10) Pageable pageable) throws Exception {
        try {
            return integralLogService.getIntegralLogDeclaring(studentNum, pageable);
        } catch (Exception e) {
            LOGGER.info("getIntegralLogDeclaring:" + e.getMessage());
            throw new Exception(e.getMessage());
        }
    }
}
