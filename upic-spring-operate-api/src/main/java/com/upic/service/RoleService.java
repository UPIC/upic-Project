package com.upic.service;

import com.upic.condition.RoleCondition;
import com.upic.dto.RoleInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by zhubuqing on 2017/9/7.
 */
public interface RoleService {
    /**
     * 添加角色
     *
     * @param roleInfo
     * @return
     */
    RoleInfo addRole(RoleInfo roleInfo);

    /**
     * 更新角色内容
     *
     * @param roleInfo
     * @return
     */
    RoleInfo updateRole(RoleInfo roleInfo);

    /**
     * 查询角色（条件）
     *
     * @param roleCondition
     * @param pageable
     * @return
     */
    Page<RoleInfo> searchRole(RoleCondition roleCondition, Pageable pageable);

    /**
     * 根据ID查询角色
     *
     * @param roleId
     * @return
     */
    RoleInfo getRoleById(long roleId);

    /**
     * 删除角色
     *
     * @param roleId
     */
    void deleteRole(long roleId);
}
