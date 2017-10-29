package com.upic.condition;

import com.upic.common.base.condition.BaseCondition;
import com.upic.enums.RoleResourceStatusEnum;
import com.upic.enums.RoleResourceTypeEnum;

/**
 * Created by zhubuqing on 2017/9/6.
 */
public class RoleResourceCondition extends BaseCondition {
    private long roleId; //角色

    private String resourceNum; //菜单编号

    private RoleResourceStatusEnum status; //状态

    private RoleResourceTypeEnum type; //类型

    public RoleResourceCondition() {
        super();
    }

    public RoleResourceCondition(long roleId, String resourceNum, RoleResourceStatusEnum status, RoleResourceTypeEnum type) {
        this.roleId = roleId;
        this.resourceNum = resourceNum;
        this.status = status;
        this.type = type;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public String getResourceNum() {
        return resourceNum;
    }

    public void setResourceNum(String resourceNum) {
        this.resourceNum = resourceNum;
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
