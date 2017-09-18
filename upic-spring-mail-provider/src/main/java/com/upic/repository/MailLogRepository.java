package com.upic.repository;

import com.upic.po.MailLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by zhubuqing on 2017/9/8.
 */
public interface MailLogRepository extends JpaRepository<MailLog, Long>, JpaSpecificationExecutor<MailLog> {

}