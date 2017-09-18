package com.upic.dto;

import com.upic.common.base.dto.BaseInfo;
import com.upic.enums.CategoryNodeTypeEnum;

/**
 * Created by zhubuqing on 2017/8/4.
 */
public class CategoryNodeInfo extends BaseInfo {
    private int longNum; //占长（也许有用）

    private int widthNum; //占宽（也许有用）

    private long fatherId; //上一级ID

    private String nodeContent; //节点内容

    private CategoryNodeTypeEnum type; //节点类型

    private int level; //层级

    private int isLeaf; //是否叶子节点

    public CategoryNodeInfo() {
        super();
    }

    public CategoryNodeInfo(int longNum, int widthNum, long fatherId, String nodeContent, CategoryNodeTypeEnum type, int level, int isLeaf) {
        this.longNum = longNum;
        this.widthNum = widthNum;
        this.fatherId = fatherId;
        this.nodeContent = nodeContent;
        this.type = type;
        this.level = level;
        this.isLeaf = isLeaf;
    }

    public int getLongNum() {
        return longNum;
    }

    public void setLongNum(int longNum) {
        this.longNum = longNum;
    }

    public int getWidthNum() {
        return widthNum;
    }

    public void setWidthNum(int widthNum) {
        this.widthNum = widthNum;
    }

    public long getFatherId() {
        return fatherId;
    }

    public void setFatherId(long fatherId) {
        this.fatherId = fatherId;
    }

    public String getNodeContent() {
        return nodeContent;
    }

    public void setNodeContent(String nodeContent) {
        this.nodeContent = nodeContent;
    }

    public CategoryNodeTypeEnum getType() {
        return type;
    }

    public void setType(CategoryNodeTypeEnum type) {
        this.type = type;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(int isLeaf) {
        this.isLeaf = isLeaf;
    }

    @Override
    public String toString() {
        return "CategoryNodeInfo{" +
                "longNum=" + longNum +
                ", widthNum=" + widthNum +
                ", fatherId=" + fatherId +
                ", nodeContent='" + nodeContent + '\'' +
                ", type=" + type +
                ", level=" + level +
                '}';
    }
}
