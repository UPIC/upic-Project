package com.upic.repository;

import com.upic.dto.RoleInfo;
import com.upic.po.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by zhubuqing on 2017/9/7.
 */
public interface RoleRepository extends JpaRepository<Role, Long>, JpaSpecificationExecutor<Role> {

    @Query(value = "select role from Role role, OperatorRole operatorRole where role.id = operatorRole.roleId and operatorRole.jobNum = ?1")
    List<Role> getRoleByJobNum(String userNum);
}