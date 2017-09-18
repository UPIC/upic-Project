package com.upic.repository;

import com.upic.po.RoleResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by zhubuqing on 2017/9/7.
 */
public interface RoleResourceRepository extends JpaRepository<RoleResource, Long>, JpaSpecificationExecutor<RoleResource> {

}