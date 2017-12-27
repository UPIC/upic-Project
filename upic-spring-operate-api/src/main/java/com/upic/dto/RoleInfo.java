package com.upic.dto;

import com.upic.common.base.dto.BaseInfo;
import com.upic.enums.RoleStatusEnum;
import com.upic.enums.RoleTypeEnum;

/**
 * Created by zhubuqing on 2017/8/4.
 */
public class RoleInfo extends BaseInfo {
    private String roleName; // 角色名

    private RoleStatusEnum status; // 角色状态

    private RoleTypeEnum type; // 角色类型

    private String content; // 描述

    private String aliasName; // 别名

    private int rank; // 等级

    public RoleInfo() {
        super();
    }

    public RoleInfo(String roleName, RoleStatusEnum status, RoleTypeEnum type, String content, String aliasName, int rank) {
        this.roleName = roleName;
        this.status = status;
        this.type = type;
        this.content = content;
        this.aliasName = aliasName;
        this.rank = rank;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "RoleInfo{" +
                "roleName='" + roleName + '\'' +
                ", status=" + status +
                ", type=" + type +
                ", content='" + content + '\'' +
                ", aliasName='" + aliasName + '\'' +
                ", rank=" + rank +
                '}';
    }
}
