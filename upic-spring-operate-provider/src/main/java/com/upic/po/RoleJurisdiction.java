package com.upic.po;

import com.upic.common.base.entiy.BaseEntity;
import com.upic.enums.RoleJurisdictionStatusEnum;
import com.upic.enums.RoleJurisdictionTypeEnum;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by zhubuqing on 2017/8/4.
 */
@Data
@Entity
public class RoleJurisdiction extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true, nullable = false, updatable = false)
    private Role role; //角色

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true, nullable = false, updatable = false)
    private Jurisdiction jurisdiction; //权限

    private RoleJurisdictionStatusEnum status; //状态

    private RoleJurisdictionTypeEnum type; //类型
}
