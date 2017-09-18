package com.upic.repository;

import com.upic.po.OperatorRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by zhubuqing on 2017/9/7.
 */
public interface OperatorRoleRepository extends JpaRepository<OperatorRole, Long>, JpaSpecificationExecutor<OperatorRole> {

}