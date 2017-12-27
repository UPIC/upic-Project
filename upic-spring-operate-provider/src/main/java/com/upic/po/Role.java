package com.upic.po;

import com.upic.common.base.entiy.BaseEntity;
import com.upic.enums.RoleStatusEnum;
import com.upic.enums.RoleTypeEnum;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by zhubuqing on 2017/8/4.
 */
@Data
@Entity
public class Role extends BaseEntity {
    private String roleName; //角色名

    private String content; //角色描述

    @Enumerated(EnumType.STRING)
    private RoleStatusEnum status; //角色状态

    @Enumerated(EnumType.STRING)
    private RoleTypeEnum type; //角色类型
    
    private String aliasName; //别名

    private int rank;
}
