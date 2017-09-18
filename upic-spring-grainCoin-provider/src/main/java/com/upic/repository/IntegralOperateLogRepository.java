package com.upic.repository;

import com.upic.po.IntegralOperateLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by zhubuqing on 2017/9/8.
 */
public interface IntegralOperateLogRepository extends JpaRepository<IntegralOperateLog, Long>, JpaSpecificationExecutor<IntegralOperateLog> {

}