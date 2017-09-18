package com.upic.repository;

import com.upic.po.Advice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by zhubuqing on 2017/9/8.
 */
public interface AdviceRepository extends JpaRepository<Advice, Long>, JpaSpecificationExecutor<Advice> {

}