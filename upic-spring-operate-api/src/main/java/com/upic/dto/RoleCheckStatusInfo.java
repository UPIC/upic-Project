package com.upic.dto;

import com.upic.common.base.dto.BaseInfo;

/**
 * Created by zhubuqing on 2017/8/4.
 */
public class RoleCheckStatusInfo extends BaseInfo {
    private String roleName; //角色名

    private String enumName;

    private String checkStatusName;

    private int num;

    private long roleId;

    public RoleCheckStatusInfo() {
    }

    public RoleCheckStatusInfo(String roleName, String enumName, String checkStatusName, int num, long roleId) {
        this.roleName = roleName;
        this.enumName = enumName;
        this.checkStatusName = checkStatusName;
        this.num = num;
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getEnumName() {
        return enumName;
    }

    public void setEnumName(String enumName) {
        this.enumName = enumName;
    }

    public String getCheckStatusName() {
        return checkStatusName;
    }

    public void setCheckStatusName(String checkStatusName) {
        this.checkStatusName = checkStatusName;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "RoleCheckStatusInfo{" +
                "roleName='" + roleName + '\'' +
                ", enumName='" + enumName + '\'' +
                ", checkStatusName='" + checkStatusName + '\'' +
                ", num=" + num +
                ", roleId=" + roleId +
                '}';
    }
}
