package com.upic.condition;

import com.upic.common.base.condition.BaseCondition;
import com.upic.enums.OperatorRoleStatusEnum;
import com.upic.enums.OperatorRoleTypeEnum;

/**
 * Created by zhubuqing on 2017/9/6.
 */
public class OperatorRoleCondition extends BaseCondition {
    private OperatorRoleStatusEnum status; //状态

    private OperatorRoleTypeEnum type; //类型

    public OperatorRoleCondition() {
        super();
    }

    public OperatorRoleCondition(OperatorRoleStatusEnum status, OperatorRoleTypeEnum type) {
        super();
        this.status = status;
        this.type = type;
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
