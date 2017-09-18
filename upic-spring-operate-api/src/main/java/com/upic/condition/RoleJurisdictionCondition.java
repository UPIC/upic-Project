package com.upic.condition;

import com.upic.common.base.condition.BaseCondition;
import com.upic.enums.RoleJurisdictionStatusEnum;
import com.upic.enums.RoleJurisdictionTypeEnum;

/**
 * Created by zhubuqing on 2017/9/6.
 */
public class RoleJurisdictionCondition extends BaseCondition {
    private RoleJurisdictionStatusEnum status; //状态

    private RoleJurisdictionTypeEnum type; //类型

    public RoleJurisdictionCondition() {
        super();
    }

    public RoleJurisdictionCondition(RoleJurisdictionStatusEnum status, RoleJurisdictionTypeEnum type) {
        super();
        this.status = status;
        this.type = type;
    }

    public RoleJurisdictionStatusEnum getStatus() {
        return status;
    }

    public void setStatus(RoleJurisdictionStatusEnum status) {
        this.status = status;
    }

    public RoleJurisdictionTypeEnum getType() {
        return type;
    }

    public void setType(RoleJurisdictionTypeEnum type) {
        this.type = type;
    }
}
