package com.upic.dto;

import com.upic.common.base.dto.BaseInfo;
import com.upic.enums.ResourceStatusEnum;
import com.upic.enums.ResourceTypeEnum;

/**
 * Created by zhubuqing on 2017/8/4.
 */
public class ResourceInfo extends BaseInfo {
    private String resourceNum; //菜单编号

    private String resourceName; //菜单名称

    private String url; //URL

    private ResourceStatusEnum status; //状态

    private ResourceTypeEnum type; //类型

    private int level; //层级

    private long fatherId; //上级ID

    private int isLeaf; //是否叶子

    private int num;

    public ResourceInfo() {
        super();
    }

    public ResourceInfo(String resourceNum, String resourceName, String url, ResourceStatusEnum status, ResourceTypeEnum type, int level, long fatherId, int isLeaf, int num) {
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public long getFatherId() {
        return fatherId;
    }

    public void setFatherId(long fatherId) {
        this.fatherId = fatherId;
    }

    public int getIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(int isLeaf) {
        this.isLeaf = isLeaf;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "ResourceInfo{" +
                "resourceNum='" + resourceNum + '\'' +
                ", resourceName='" + resourceName + '\'' +
                ", url='" + url + '\'' +
                ", status=" + status +
                ", type=" + type +
                ", level=" + level +
                ", fatherId=" + fatherId +
                ", isLeaf=" + isLeaf +
                ", num=" + num +
                '}';
    }
}
