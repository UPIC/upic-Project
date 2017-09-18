package com.upic.condition;

import com.upic.common.base.condition.BaseCondition;
import com.upic.enums.CategoryNodeTypeEnum;

/**
 * Created by zhubuqing on 2017/9/6.
 */
public class CategoryNodeCondition extends BaseCondition {
    private Long fatherId; //上一级ID

    private String nodeContent; //节点内容

    private CategoryNodeTypeEnum type; //节点类型

    private Integer level; //层级

    private Integer isLeaf; //是否叶子节点

    public CategoryNodeCondition() {
        super();
    }

    public CategoryNodeCondition(Long fatherId, String nodeContent, CategoryNodeTypeEnum type, Integer level, Integer isLeaf) {
        this.fatherId = fatherId;
        this.nodeContent = nodeContent;
        this.type = type;
        this.level = level;
        this.isLeaf = isLeaf;
    }

    public Long getFatherId() {
        return fatherId;
    }

    public void setFatherId(Long fatherId) {
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

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(Integer isLeaf) {
        this.isLeaf = isLeaf;
    }
}
