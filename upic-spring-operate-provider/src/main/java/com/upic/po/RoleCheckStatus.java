package com.upic.po;

import com.upic.common.base.entiy.BaseEntity;
import lombok.Data;

import javax.persistence.*;

/**
 * 审批类型与角色中间表
 * Created by zhubuqing on 2017/8/4.
 */
@Data
@Entity
public class RoleCheckStatus extends BaseEntity {
    private String roleName; // 角色名

    private String enumName; // 审批状态英文名

    private String checkStatusName; // 审批状态中文名

    private int num; // 排序

    private long roleId; // 角色ID
}
