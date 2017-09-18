package com.upic.service;

import com.upic.condition.MailLogCondition;
import com.upic.dto.MailLogInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by zhubuqing on 2017/9/12.
 */
public interface MailLogService {
    /**
     * 添加邮件日志
     *
     * @param mailLogInfo
     * @return
     */
    MailLogInfo addMailLog(MailLogInfo mailLogInfo);

    /**
     * 修改邮件日志
     *
     * @param mailLogInfo
     * @return
     */
    MailLogInfo updateMailLog(MailLogInfo mailLogInfo);

    /**
     * 查询邮件日志（条件）
     *
     * @param mailLogCondition
     * @param pageable
     * @return
     */
    Page<MailLogInfo> searchMailLog(MailLogCondition mailLogCondition, Pageable pageable);

    /**
     * 单个查询邮件日志
     *
     * @param mailLogId
     * @return
     */
    MailLogInfo getByMailLogId(long mailLogId);

    /**
     * 删除邮件日志
     *
     * @param mailLogId
     */
    void deleteMailLog(long mailLogId);
}
