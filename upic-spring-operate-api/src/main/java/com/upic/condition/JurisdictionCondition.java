package com.upic.condition;

import com.upic.common.base.condition.BaseCondition;

/**
 * Created by zhubuqing on 2017/9/6.
 */
public class JurisdictionCondition extends BaseCondition {
    private String jurisdictionName; //权限名称

    private Long resourceId; //菜单ID

    private String remark; //备注

    private String url; //URL

    private String status; //状态

    private String type; // 类型

    public JurisdictionCondition() {
        super();
    }

    public JurisdictionCondition(String jurisdictionName, Long resourceId, String remark, String url, String status, String type) {
        super();
        this.jurisdictionName = jurisdictionName;
        this.resourceId = resourceId;
        this.remark = remark;
        this.url = url;
        this.status = status;
        this.type = type;
    }

    public String getJurisdictionName() {
        return jurisdictionName;
    }

    public void setJurisdictionName(String jurisdictionName) {
        this.jurisdictionName = jurisdictionName;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
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
}
