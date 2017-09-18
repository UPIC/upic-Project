package com.upic.po;

import com.upic.common.base.entiy.BaseEntity;
import com.upic.enums.OperatorRoleStatusEnum;
import com.upic.enums.OperatorRoleTypeEnum;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by zhubuqing on 2017/8/4.
 */
@Data
@Entity
public class OperatorRole extends BaseEntity {
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, targetEntity = Operator.class)
    @JoinColumn(unique = true, nullable = false, updatable = false)
    private Operator operator; //操作员

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, targetEntity = Role.class)
    @JoinColumn(unique = true, nullable = false, updatable = false)
    private Role role; //角色

    private OperatorRoleStatusEnum status; //状态

    private OperatorRoleTypeEnum type; //类型
}
