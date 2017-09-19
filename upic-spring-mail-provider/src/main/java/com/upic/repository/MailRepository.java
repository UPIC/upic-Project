package com.upic.repository;

import com.upic.po.Mail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by zhubuqing on 2017/9/7.
 */
public interface MailRepository extends JpaRepository<Mail, Long>, JpaSpecificationExecutor<Mail> {

    /**
     * 用户获取站内信
     *
     * @param college
     * @param major
     * @param clazz
     * @param userNum
     * @param pageable
     * @return
     */
    @Query(value = "select mail from Mail mail where mail.target = ?1 or mail.target = ?2 or mail.target = ?3 or mail.target = ?4")
    Page<Mail> getMyMail(String college, String major, String clazz, String userNum, Pageable pageable);
}