package com.upic.dto;

import com.upic.common.base.dto.BaseInfo;
import com.upic.enums.RoleJurisdictionStatusEnum;
import com.upic.enums.RoleJurisdictionTypeEnum;

/**
 * Created by zhubuqing on 2017/8/4.
 */
public class RoleJurisdictionInfo extends BaseInfo {
    private RoleInfo role; //角色

    private JurisdictionInfo jurisdiction; //权限

    private RoleJurisdictionStatusEnum status; //状态

    private RoleJurisdictionTypeEnum type; //类型

    public RoleJurisdictionInfo() {
        super();
    }

    public RoleJurisdictionInfo(RoleInfo role, JurisdictionInfo jurisdiction, RoleJurisdictionStatusEnum status, RoleJurisdictionTypeEnum type) {
        super();
        this.role = role;
        this.jurisdiction = jurisdiction;
        this.status = status;
        this.type = type;
    }

    public RoleInfo getRole() {
        return role;
    }

    public void setRole(RoleInfo role) {
        this.role = role;
    }

    public JurisdictionInfo getJurisdiction() {
        return jurisdiction;
    }

    public void setJurisdiction(JurisdictionInfo jurisdiction) {
        this.jurisdiction = jurisdiction;
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

    @Override
    public String toString() {
        return "RoleJurisdictionInfo{" +
                "role=" + role +
                ", jurisdiction=" + jurisdiction +
                ", status=" + status +
                ", type=" + type +
                '}';
    }
}
