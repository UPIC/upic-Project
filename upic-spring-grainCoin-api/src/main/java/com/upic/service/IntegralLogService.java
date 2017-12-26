package com.upic.service;

import com.upic.condition.IntegralLogCondition;
import com.upic.dto.IntegralLogIdInfo;
import com.upic.dto.IntegralLogInfo;
import com.upic.enums.IntegralLogStatusEnum;
import com.upic.enums.IntegralLogTypeEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Queue;

/**
 * Created by zhubuqing on 2017/9/6.
 */
public interface IntegralLogService {
    /**
     * 全部操作积分（不多用）
     *
     * @param apartment
     * @param operatorNum
     * @param status
     * @param type
     */
    void allOperation(String apartment, String operatorNum, IntegralLogStatusEnum status, IntegralLogTypeEnum type);

    /**
     * 部分操作积分
     *
     * @param queue
     * @param operatorNum
     * @param status
     * @param type
     * @return
     */
    Queue<Long> someOperation(Queue<Long> queue, String operatorNum, IntegralLogStatusEnum status, IntegralLogTypeEnum type);

    /**
     * 储存积分
     *
     * @param integralLogInfo
     * @return
     */
    IntegralLogInfo saveIntegralLog(IntegralLogInfo integralLogInfo);

    /**
     * 查看积分
     *
     * @param studentNum
     * @return
     */
    double watchIntegral(String studentNum);

    /**
     * 根据积分日志ID获取积分日志详情
     *
     * @param integralLogIdInfo
     * @return
     */
    IntegralLogInfo getByIntegralLogId(IntegralLogIdInfo integralLogIdInfo);



    /**
     * 报名
     *
     * @param integralLogInfo
     */
    IntegralLogInfo signUp(IntegralLogInfo integralLogInfo);

    /**
     * 查询积分列表（条件）
     *
     * @param integralLogCondition
     * @param pageable
     * @return
     */
    Page<IntegralLogInfo> searchIntegralLog(IntegralLogCondition integralLogCondition, Pageable pageable);

    /**
     * 根据项目编号查询参加用户
     *
     * @param projectNum
     * @param pageable
     * @return
     */
    Page<IntegralLogInfo> getUserListByProjectNum(String projectNum, Pageable pageable);

    /**
     * 根据项目编号查询报名人数
     *
     * @param projectNum
     * @return
     */
    int getSignUpNumberByProjectNum(String projectNum);

    /**
     * 根据学生学号查找参加过的项目
     *
     * @param studentNum
     * @param pageable
     * @return
     */
    Page<IntegralLogInfo> getIntegralLogByMySelf(String studentNum, Pageable pageable);

    /**
     * 学生查看自身的积分日志
     *
     * @param studentNum
     * @param pageable
     * @return
     */
    Page<IntegralLogInfo> getAllIntegralLogByStudentNum(String studentNum, Pageable pageable);

    /**
     * 获取申报中的积分
     *
     * @param studentNum
     * @param pageable
     * @return
     */
    Page<IntegralLogInfo> getIntegralLogDeclaring(String studentNum, Pageable pageable);

    /**
     * 积分搜索条
     *
     * @param status
     * @param keyword
     * @param pageable
     * @return
     */
    Page<IntegralLogInfo> integralLogSearchBar(IntegralLogStatusEnum status, String keyword, Pageable pageable);

    /**
     * 修改积分状态
     *
     * @param integralLogIdInfos
     * @param status
     * @return
     */
    String updateIntegralLogStatus(List<IntegralLogIdInfo> integralLogIdInfos, IntegralLogStatusEnum status);

    /**
     * 根据项目编号获取参加列表
     *
     * @param projectNum
     * @return
     */
    List<IntegralLogInfo> getByProjectNum(String projectNum);

    /**
     * 获取未通过审核的积分申请
     *
     * @param status
     * @param pageable
     * @return
     */
    Page<IntegralLogInfo> getIntegralLogWithOutPass(IntegralLogStatusEnum status, Pageable pageable);

    void saveAll(List<Object> list);

    List<Object> listIntegralLog(IntegralLogCondition condition);

    Page<IntegralLogInfo> getIntegralLogBySql(List<String> statusList, List<String> projectCategoryList, String rank, String colloge, Pageable pageable);

    IntegralLogInfo changeAllIntegralLogStatus(IntegralLogInfo integralLogInfo);

    void addAll(List<IntegralLogInfo> integralLogInfoList);

    /**
     * 二维码消费
     *
     * @param integralLogInfo
     * @param accessToken
     * @return
     */
    String qrCodeConsumption(IntegralLogInfo integralLogInfo, String accessToken);

    Page<IntegralLogInfo> getIntegralLogByStudentNum(String userId, Pageable pageable);

    Page<IntegralLogInfo> getInreviewIntegralLogPage(String studentNum, Pageable pageable);

    Page<IntegralLogInfo> getSuccessIntegralLogPage(String studentNum, Pageable pageable);

    Page<IntegralLogInfo> getDefeatedIntegralLogPage(String studentNum, Pageable pageable);

    String changeIntegralLogToSignedIn(String projectNum, String userId);
}
