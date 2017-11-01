package com.upic.repository;

import com.upic.po.RoleResource;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by zhubuqing on 2017/9/7.
 */
public interface RoleResourceRepository extends JpaRepository<RoleResource, Long>, JpaSpecificationExecutor<RoleResource> {
	List<RoleResource> getByRoleId(long roleId);
}