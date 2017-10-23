package com.upic.repository;

import com.upic.po.College;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by zhubuqing on 2017/10/20.
 */
public interface CollegeRepository extends JpaRepository<College, Long>, JpaSpecificationExecutor<College> {
}
