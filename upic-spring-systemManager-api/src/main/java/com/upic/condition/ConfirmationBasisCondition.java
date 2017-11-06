package com.upic.condition;

import com.upic.common.base.condition.BaseCondition;

/**
 * Created by zhubuqing on 2017/9/6.
 */
public class ConfirmationBasisCondition extends BaseCondition {
    private String content; //内容

    private String projectNum;

    private String projectName;

    private String otherName;

    private Long categoryNodeId;

    public ConfirmationBasisCondition() {
        super();
    }

    public ConfirmationBasisCondition(String content, String projectNum, String projectName, String otherName, Long categoryNodeId) {
        this.content = content;
        this.projectNum = projectNum;
        this.projectName = projectName;
        this.otherName = otherName;
        this.categoryNodeId = categoryNodeId;
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

    public Long getCategoryNodeId() {
        return categoryNodeId;
    }

    public void setCategoryNodeId(long categoryNodeId) {
        this.categoryNodeId = categoryNodeId;
    }
}
