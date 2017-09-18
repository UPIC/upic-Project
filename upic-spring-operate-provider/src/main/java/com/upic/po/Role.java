package com.upic.po;

import com.upic.common.base.entiy.BaseEntity;
import com.upic.enums.RoleStatusEnum;
import com.upic.enums.RoleTypeEnum;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * Created by zhubuqing on 2017/8/4.
 */
@Data
@Entity
public class Role extends BaseEntity {
    private String roleName; //角色名

    @Enumerated(EnumType.STRING)
    private RoleStatusEnum status; //角色状态

    @Enumerated(EnumType.STRING)
    private RoleTypeEnum type; //角色类型

    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, mappedBy = "role")
    private List<RoleResource> roleResources;

    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, mappedBy = "role")
    private List<OperatorRole> operatorRoles;

    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, mappedBy = "role")
    private List<RoleJurisdiction> roleJurisdictions;
}
