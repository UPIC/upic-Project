package com.upic.service;

import com.upic.condition.OperatorRoleCondition;
import com.upic.dto.OperatorRoleInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by zhubuqing on 2017/9/7.
 */
public interface OperatorRoleService {
    /**
     * 添加账号角色关系
     *
     * @param operatorRoleInfo
     * @return
     */
    OperatorRoleInfo addOperatorRole(OperatorRoleInfo operatorRoleInfo);

    /**
     * 查询账号角色关系
     *
     * @param operatorRoleCondition
     * @param pageable
     * @return
     */
    Page<OperatorRoleInfo> searchOperatorRole(OperatorRoleCondition operatorRoleCondition, Pageable pageable);

    /**
     * 删除账号角色关系
     *
     * @param operatorRoleInfo
     */
    void deleteOperatorRole(OperatorRoleInfo operatorRoleInfo);

    List<OperatorRoleInfo> getByJobNum(String jobNum);

    OperatorRoleInfo getByJobNumAndRoleId(String jobNum, Long aLong);

    List<OperatorRoleInfo> getByRoleId(long roleId);
}
