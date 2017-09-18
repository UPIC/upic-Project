package com.upic.dto;

import com.upic.common.base.dto.BaseInfo;
import com.upic.enums.RoleResourceStatusEnum;
import com.upic.enums.RoleResourceTypeEnum;

/**
 * Created by zhubuqing on 2017/8/4.
 */
public class RoleResourceInfo extends BaseInfo {
    private RoleInfo role; //角色

    private ResourceInfo resource; //菜单

    private RoleResourceStatusEnum status; //状态

    private RoleResourceTypeEnum type; //类型

    public RoleResourceInfo() {
        super();
    }

    public RoleResourceInfo(RoleInfo role, ResourceInfo resource, RoleResourceStatusEnum status, RoleResourceTypeEnum type) {
        super();
        this.role = role;
        this.resource = resource;
        this.status = status;
        this.type = type;
    }

    public RoleInfo getRole() {
        return role;
    }

    public void setRole(RoleInfo role) {
        this.role = role;
    }

    public ResourceInfo getResource() {
        return resource;
    }

    public void setResource(ResourceInfo resource) {
        this.resource = resource;
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
                "role=" + role +
                ", resource=" + resource +
                ", status=" + status +
                ", type=" + type +
                '}';
    }
}
