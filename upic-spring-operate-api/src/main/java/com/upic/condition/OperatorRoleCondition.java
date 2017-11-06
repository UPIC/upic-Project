package com.upic.condition;

import com.upic.common.base.condition.BaseCondition;
import com.upic.enums.OperatorRoleStatusEnum;
import com.upic.enums.OperatorRoleTypeEnum;

/**
 * Created by zhubuqing on 2017/9/6.
 */
public class OperatorRoleCondition extends BaseCondition {
    private String jobNum; //操作员

    private Long roleId;

    private OperatorRoleStatusEnum status; //状态

    private OperatorRoleTypeEnum type; //类型

    public OperatorRoleCondition() {
        super();
    }

    public OperatorRoleCondition(String jobNum, Long roleId, OperatorRoleStatusEnum status, OperatorRoleTypeEnum type) {
        this.jobNum = jobNum;
        this.roleId = roleId;
        this.status = status;
        this.type = type;
    }

    public String getJobNum() {
        return jobNum;
    }

    public void setJobNum(String jobNum) {
        this.jobNum = jobNum;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
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
}
