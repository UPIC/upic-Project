package com.upic.repository;

import com.upic.po.RoleCheckStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by zhubuqing on 2017/9/7.
 */
public interface RoleCheckStatusRepository extends JpaRepository<RoleCheckStatus, Long>, JpaSpecificationExecutor<RoleCheckStatus> {

}