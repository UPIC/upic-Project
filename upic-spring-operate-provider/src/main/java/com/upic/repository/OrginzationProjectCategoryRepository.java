package com.upic.repository;

import com.upic.po.Orginzation;
import com.upic.po.OrginzationProjectCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by zhubuqing on 2017/9/7.
 */
public interface OrginzationProjectCategoryRepository extends JpaRepository<OrginzationProjectCategory, Long>, JpaSpecificationExecutor<OrginzationProjectCategory> {

}