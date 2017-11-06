package com.upic.condition;

import com.upic.common.base.condition.BaseCondition;

/**
 * Created by zhubuqing on 2017/9/6.
 */
public class RoleCheckStatusCondition extends BaseCondition {
    private String roleName; //角色名

    private String enumName;

    private String checkStatusName;

    private Integer num;

    private Long roleId;

    public RoleCheckStatusCondition() {
    }

    public RoleCheckStatusCondition(String roleName, String enumName, String checkStatusName, Integer num, Long roleId) {
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

    public Integer getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }
}
