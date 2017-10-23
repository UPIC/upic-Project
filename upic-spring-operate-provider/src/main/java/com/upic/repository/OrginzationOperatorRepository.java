package com.upic.repository;

import com.upic.po.OrginzationOperator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by zhubuqing on 2017/9/7.
 */
public interface OrginzationOperatorRepository extends JpaRepository<OrginzationOperator, Long>, JpaSpecificationExecutor<OrginzationOperator> {

}