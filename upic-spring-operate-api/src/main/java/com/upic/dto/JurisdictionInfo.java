package com.upic.dto;

import com.upic.common.base.dto.BaseInfo;

import java.util.List;

/**
 * Created by zhubuqing on 2017/8/4.
 */
public class JurisdictionInfo extends BaseInfo {
    private String jurisdictionName; //权限名称

    private long resourceId; //菜单ID

    private String remark; //备注

    private String url; //URL

    private String status; //状态

    private String type; // 类型

    private List<RoleJurisdictionInfo> roleJurisdictions;

    public JurisdictionInfo() {
        super();
    }

    public JurisdictionInfo(String jurisdictionName, long resourceId, String remark, String url, String status, String type, List<RoleJurisdictionInfo> roleJurisdictions) {
        super();
        this.jurisdictionName = jurisdictionName;
        this.resourceId = resourceId;
        this.remark = remark;
        this.url = url;
        this.status = status;
        this.type = type;
        this.roleJurisdictions = roleJurisdictions;
    }

    public String getJurisdictionName() {
        return jurisdictionName;
    }

    public void setJurisdictionName(String jurisdictionName) {
        this.jurisdictionName = jurisdictionName;
    }

    public long getResourceId() {
        return resourceId;
    }

    public void setResourceId(long resourceId) {
        this.resourceId = resourceId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<RoleJurisdictionInfo> getRoleJurisdictions() {
        return roleJurisdictions;
    }

    public void setRoleJurisdictions(List<RoleJurisdictionInfo> roleJurisdictions) {
        this.roleJurisdictions = roleJurisdictions;
    }

    @Override
    public String toString() {
        return "JurisdictionInfo{" +
                "jurisdictionName='" + jurisdictionName + '\'' +
                ", resourceId=" + resourceId +
                ", remark='" + remark + '\'' +
                ", url='" + url + '\'' +
                ", status='" + status + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
