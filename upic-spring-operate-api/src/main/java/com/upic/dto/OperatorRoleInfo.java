package com.upic.dto;

import com.upic.common.base.dto.BaseInfo;
import com.upic.enums.OperatorRoleStatusEnum;
import com.upic.enums.OperatorRoleTypeEnum;

import java.util.Date;

/**
 * Created by zhubuqing on 2017/8/4.
 */
public class OperatorRoleInfo extends BaseInfo {
    private OperatorInfo operator; //操作员

    private RoleInfo role; //角色

    private OperatorRoleStatusEnum status; //状态

    private OperatorRoleTypeEnum type; //类型

    public OperatorRoleInfo() {
        super();
    }

    public OperatorRoleInfo(Long id, Date creatTime, String field1, String field2, String field3, String field4, String field5, OperatorInfo operator, RoleInfo role, OperatorRoleStatusEnum status, OperatorRoleTypeEnum type) {
        super();
        this.operator = operator;
        this.role = role;
        this.status = status;
        this.type = type;
    }

    public OperatorInfo getOperator() {
        return operator;
    }

    public void setOperator(OperatorInfo operator) {
        this.operator = operator;
    }

    public RoleInfo getRole() {
        return role;
    }

    public void setRole(RoleInfo role) {
        this.role = role;
    }

    public OperatorRoleStatusEnum getStatus() {
        return status;
    }

    public void setStatus(OperatorRoleStatusEnum status) {
        this.status = status;
    }

    public OperatorRoleTypeEnum getType() {
        return type;
    }

    public void setType(OperatorRoleTypeEnum type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "OperatorRoleInfo{" +
                "operator=" + operator +
                ", role=" + role +
                ", status=" + status +
                ", type=" + type +
                '}';
    }
}
