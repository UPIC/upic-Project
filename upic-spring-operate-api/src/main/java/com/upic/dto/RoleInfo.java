package com.upic.dto;

import com.upic.common.base.dto.BaseInfo;
import com.upic.enums.RoleStatusEnum;
import com.upic.enums.RoleTypeEnum;

import java.util.List;

/**
 * Created by zhubuqing on 2017/8/4.
 */
public class RoleInfo extends BaseInfo {
    private String roleName; //角色名

    private RoleStatusEnum status; //角色状态

    private RoleTypeEnum type; //角色类型

    private List<RoleResourceInfo> roleResources;

    private List<OperatorRoleInfo> operatorRoles;

    private List<RoleJurisdictionInfo> roleJurisdictions;

    public RoleInfo() {
        super();
    }

    public RoleInfo(String roleName, RoleStatusEnum status, RoleTypeEnum type, List<RoleResourceInfo> roleResources, List<OperatorRoleInfo> operatorRoles, List<RoleJurisdictionInfo> roleJurisdictions) {
        super();
        this.roleName = roleName;
        this.status = status;
        this.type = type;
        this.roleResources = roleResources;
        this.operatorRoles = operatorRoles;
        this.roleJurisdictions = roleJurisdictions;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public RoleStatusEnum getStatus() {
        return status;
    }

    public void setStatus(RoleStatusEnum status) {
        this.status = status;
    }

    public RoleTypeEnum getType() {
        return type;
    }

    public void setType(RoleTypeEnum type) {
        this.type = type;
    }

    public List<RoleResourceInfo> getRoleResources() {
        return roleResources;
    }

    public void setRoleResources(List<RoleResourceInfo> roleResources) {
        this.roleResources = roleResources;
    }

    public List<OperatorRoleInfo> getOperatorRoles() {
        return operatorRoles;
    }

    public void setOperatorRoles(List<OperatorRoleInfo> operatorRoles) {
        this.operatorRoles = operatorRoles;
    }

    public List<RoleJurisdictionInfo> getRoleJurisdictions() {
        return roleJurisdictions;
    }

    public void setRoleJurisdictions(List<RoleJurisdictionInfo> roleJurisdictions) {
        this.roleJurisdictions = roleJurisdictions;
    }

    @Override
    public String toString() {
        return "RoleInfo{" +
                "roleName='" + roleName + '\'' +
                ", status=" + status +
                ", type=" + type +
                '}';
    }
}
