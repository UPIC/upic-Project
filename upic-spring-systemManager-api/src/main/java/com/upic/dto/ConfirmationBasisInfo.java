package com.upic.dto;

import com.upic.common.base.dto.BaseInfo;

/**
 * Created by zhubuqing on 2017/9/4.
 */
public class ConfirmationBasisInfo extends BaseInfo {
    private String content; //内容

    private String projectNum;

    private String projectName;

    private String otherName;

    private long categoryNodeId;

    private CategoryNodeInfo categoryNode; //项目类节点

    public ConfirmationBasisInfo() {
    }

    public ConfirmationBasisInfo(String content, String projectNum, String projectName, String otherName, long categoryNodeId, CategoryNodeInfo categoryNode) {
        this.content = content;
        this.projectNum = projectNum;
        this.projectName = projectName;
        this.otherName = otherName;
        this.categoryNodeId = categoryNodeId;
        this.categoryNode = categoryNode;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getProjectNum() {
        return projectNum;
    }

    public void setProjectNum(String projectNum) {
        this.projectNum = projectNum;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getOtherName() {
        return otherName;
    }

    public void setOtherName(String otherName) {
        this.otherName = otherName;
    }

    public long getCategoryNodeId() {
        return categoryNodeId;
    }

    public void setCategoryNodeId(long categoryNodeId) {
        this.categoryNodeId = categoryNodeId;
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
                ", projectNum='" + projectNum + '\'' +
                ", projectName='" + projectName + '\'' +
                ", otherName='" + otherName + '\'' +
                ", categoryNodeId=" + categoryNodeId +
                ", categoryNode=" + categoryNode +
                '}';
    }
}
