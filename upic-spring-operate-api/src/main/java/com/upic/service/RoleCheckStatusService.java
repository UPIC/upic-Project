package com.upic.service;

import com.upic.condition.CheckStatusCondition;
import com.upic.condition.RoleCheckStatusCondition;
import com.upic.dto.CheckStatusInfo;
import com.upic.dto.RoleCheckStatusInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by zhubuqing on 2017/9/7.
 */
public interface RoleCheckStatusService {
    /**
     * 添加角色
     *
     * @return
     */
    RoleCheckStatusInfo addRoleCheckStatus(RoleCheckStatusInfo roleCheckStatusInfo);

    /**
     * 更新角色内容
     *
     * @return
     */
    RoleCheckStatusInfo updateRoleCheckStatus(RoleCheckStatusInfo roleCheckStatusInfo);

    /**
     * 查询角色（条件）
     *
     * @param pageable
     * @return
     */
    Page<RoleCheckStatusInfo> searchRoleCheckStatus(RoleCheckStatusCondition roleCheckStatusCondition, Pageable pageable);

    /**
     * 根据ID查询角色
     *
     * @return
     */
    RoleCheckStatusInfo getRoleCheckStatusById(long roleCheckStatusId);

    /**
     * 删除角色
     */
    void deleteRoleCheckStatus(long roleCheckStatusId);

    /**
     * 根据部门别名查询审批状态列表
     *
     * @return
     */
    List<String> getCheckStatusEnumName(long roleId);
}
