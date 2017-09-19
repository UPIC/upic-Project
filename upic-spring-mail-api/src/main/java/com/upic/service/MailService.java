package com.upic.service;

import com.upic.condition.MailCondition;
import com.upic.dto.MailInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by zhubuqing on 2017/9/7.
 */
public interface MailService {
    /**
     * 添加邮件
     *
     * @param mailInfo
     * @return
     */
    MailInfo addMail(MailInfo mailInfo);

    /**
     * 修改邮件
     *
     * @param mailInfo
     * @return
     */
    MailInfo updateMail(MailInfo mailInfo);

    /**
     * 查找邮件
     *
     * @param mailCondition
     * @param pageable
     * @return
     */
    Page<MailInfo> searchMail(MailCondition mailCondition, Pageable pageable);

    /**
     * 根据邮件ID查找邮件详情
     *
     * @param mailId
     * @return
     */
    MailInfo getMailById(long mailId);

    /**
     * 根据邮件ID删除邮件
     *
     * @param mailId
     */
    void deleteMail(long mailId);

    /**
     * 用户查询站内信
     *
     * @param college
     * @param major
     * @param clazz
     * @param userNum
     * @param pageable
     * @return
     */
    Page<MailInfo> getMyMail(String college, String major, String clazz, String userNum, Pageable pageable);
}
