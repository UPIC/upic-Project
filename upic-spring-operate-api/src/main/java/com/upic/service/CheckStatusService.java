package com.upic.service;

import com.upic.condition.CheckStatusCondition;
import com.upic.condition.RoleCondition;
import com.upic.dto.CheckStatusInfo;
import com.upic.dto.RoleInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by zhubuqing on 2017/9/7.
 */
public interface CheckStatusService {
    /**
     * 添加角色
     *
     * @return
     */
    CheckStatusInfo addCheckStatus(CheckStatusInfo checkStatusInfo);

    /**
     * 更新角色内容
     *
     * @return
     */
    CheckStatusInfo updateCheckStatus(CheckStatusInfo checkStatusInfo);

    /**
     * 查询角色（条件）
     *
     * @param pageable
     * @return
     */
    Page<CheckStatusInfo> searchCheckStatus(CheckStatusCondition checkStatusCondition, Pageable pageable);

    /**
     * 根据ID查询角色
     *
     * @return
     */
    CheckStatusInfo getCheckStatusById(long checkStatusId);

    /**
     * 删除角色
     */
    void deleteCheckStatus(long checkStatusId);
}
