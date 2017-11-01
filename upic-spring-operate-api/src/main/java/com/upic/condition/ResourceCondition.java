package com.upic.condition;

import com.upic.common.base.condition.BaseCondition;
import com.upic.enums.ResourceStatusEnum;
import com.upic.enums.ResourceTypeEnum;

/**
 * Created by zhubuqing on 2017/9/6.
 */
public class ResourceCondition extends BaseCondition {
    private String resourceNum; //菜单编号

    private String resourceName; //菜单名称

    private String url; //URL

    private ResourceStatusEnum status; //状态

    private ResourceTypeEnum type; //类型

    private Integer level; //层级

    private Long fatherId; //上级ID

    private Integer isLeaf; //是否叶子

    private int num;

    public ResourceCondition() {
        super();
    }

    public ResourceCondition(String resourceNum, String resourceName, String url, ResourceStatusEnum status, ResourceTypeEnum type, Integer level, Long fatherId, Integer isLeaf, int num) {
        this.resourceNum = resourceNum;
        this.resourceName = resourceName;
        this.url = url;
        this.status = status;
        this.type = type;
        this.level = level;
        this.fatherId = fatherId;
        this.isLeaf = isLeaf;
        this.num = num;
    }

    public String getResourceNum() {
        return resourceNum;
    }

    public void setResourceNum(String resourceNum) {
        this.resourceNum = resourceNum;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ResourceStatusEnum getStatus() {
        return status;
    }

    public void setStatus(ResourceStatusEnum status) {
        this.status = status;
    }

    public ResourceTypeEnum getType() {
        return type;
    }

    public void setType(ResourceTypeEnum type) {
        this.type = type;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Long getFatherId() {
        return fatherId;
    }

    public void setFatherId(Long fatherId) {
        this.fatherId = fatherId;
    }

    public Integer getIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(Integer isLeaf) {
        this.isLeaf = isLeaf;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
