package com.upic.condition;

import com.upic.common.base.condition.BaseCondition;
import com.upic.enums.RoleResourceStatusEnum;
import com.upic.enums.RoleResourceTypeEnum;

/**
 * Created by zhubuqing on 2017/9/6.
 */
public class RoleResourceCondition extends BaseCondition {
    private RoleResourceStatusEnum status; //状态

    private RoleResourceTypeEnum type; //类型

    public RoleResourceCondition() {
        super();
    }

    public RoleResourceCondition(RoleResourceStatusEnum status, RoleResourceTypeEnum type) {
        super();
        this.status = status;
        this.type = type;
    }

    public RoleResourceStatusEnum getStatus() {
        return status;
    }

    public void setStatus(RoleResourceStatusEnum status) {
        this.status = status;
    }

    public RoleResourceTypeEnum getType() {
        return type;
    }

    public void setType(RoleResourceTypeEnum type) {
        this.type = type;
    }
}
