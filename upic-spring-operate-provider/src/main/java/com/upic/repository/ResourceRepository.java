package com.upic.repository;

import com.upic.dto.ResourceInfo;
import com.upic.po.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by zhubuqing on 2017/9/7.
 */
public interface ResourceRepository extends JpaRepository<Resource, Long>, JpaSpecificationExecutor<Resource> {

    Page<Resource> findByFatherId(long fatherId, Pageable pageable);

    @Query(value = "select resource from Resource resource, RoleResource roleResource where resource.resourceNum = roleResource.resourceNum and roleResource.roleId = ?1")
    List<Resource> listResourceByRoleId(long roleId);
}