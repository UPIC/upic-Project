package com.upic.po;

import com.upic.common.base.entiy.BaseEntity;
import com.upic.enums.RoleResourceStatusEnum;
import com.upic.enums.RoleResourceTypeEnum;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by zhubuqing on 2017/8/4.
 */
@Data
@Entity
public class RoleResource extends BaseEntity {
    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, targetEntity = Role.class)
    @JoinColumn(unique = true, nullable = false, updatable = false)
    private Role role; //角色

    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, targetEntity = Resource.class)
    @JoinColumn(unique = true, nullable = false, updatable = false)
    private Resource resource; //菜单

    private RoleResourceStatusEnum status; //状态

    private RoleResourceTypeEnum type; //类型
}
