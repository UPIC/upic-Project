package com.upic.dto;

import com.upic.common.base.dto.BaseInfo;
import com.upic.enums.RoleResourceStatusEnum;
import com.upic.enums.RoleResourceTypeEnum;

/**
 * Created by zhubuqing on 2017/8/4.
 */
public class RoleResourceInfo extends BaseInfo {
    private long roleId; //角色

    private String resourceNum; //菜单编号

    private RoleResourceStatusEnum status; //状态

    private RoleResourceTypeEnum type; //类型

    public RoleResourceInfo() {
        super();
    }

    public RoleResourceInfo(long roleId, String resourceNum, RoleResourceStatusEnum status, RoleResourceTypeEnum type) {
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

    @Override
    public String toString() {
        return "RoleResourceInfo{" +
                "roleId=" + roleId +
                ", resourceNum='" + resourceNum + '\'' +
                ", status=" + status +
                ", type=" + type +
                '}';
    }
}
