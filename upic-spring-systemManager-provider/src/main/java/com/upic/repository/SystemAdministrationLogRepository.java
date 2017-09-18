package com.upic.repository;

import com.upic.po.SystemAdministrationLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by zhubuqing on 2017/9/8.
 */
public interface SystemAdministrationLogRepository extends JpaRepository<SystemAdministrationLog, Long>, JpaSpecificationExecutor<SystemAdministrationLog> {

}