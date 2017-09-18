package com.upic.dto;

import com.upic.common.base.dto.BaseInfo;

/**
 * Created by zhubuqing on 2017/9/4.
 */
public class ConfirmationBasisInfo extends BaseInfo {
    private String content; //内容

    private CategoryNodeInfo categoryNode; //项目类节点

    public ConfirmationBasisInfo() {
        super();
    }

    public ConfirmationBasisInfo(String content, CategoryNodeInfo categoryNode) {
        super();
        this.content = content;
        this.categoryNode = categoryNode;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public CategoryNodeInfo getCategoryNode() {
        return categoryNode;
    }

    public void setCategoryNode(CategoryNodeInfo categoryNode) {
        this.categoryNode = categoryNode;
    }

    @Override
    public String toString() {
        return "ConfirmationBasisInfo{" +
                "content='" + content + '\'' +
                ", categoryNode=" + categoryNode +
                '}';
    }
}
