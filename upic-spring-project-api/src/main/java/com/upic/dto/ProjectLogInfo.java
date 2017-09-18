package com.upic.dto;

import com.upic.common.base.dto.BaseInfo;

/**
 * Created by zhubuqing on 2017/8/4.
 */
public class ProjectLogInfo extends BaseInfo {
    private ProjectInfo project; //项目

    private String operation; //操作内容

    private String operatorName; //操作人

    private String operatorNum; //操作人编号

    public ProjectLogInfo() {
        super();
    }

    public ProjectLogInfo(ProjectInfo project, String operation, String operatorName, String operatorNum) {
        super();
        this.project = project;
        this.operation = operation;
        this.operatorName = operatorName;
        this.operatorNum = operatorNum;
    }

    public ProjectInfo getProject() {
        return project;
    }

    public void setProject(ProjectInfo project) {
        this.project = project;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getOperatorNum() {
        return operatorNum;
    }

    public void setOperatorNum(String operatorNum) {
        this.operatorNum = operatorNum;
    }

    @Override
    public String toString() {
        return "ProjectLogInfo{" +
                "project=" + project +
                ", operation='" + operation + '\'' +
                ", operatorName='" + operatorName + '\'' +
                ", operatorNum='" + operatorNum + '\'' +
                '}';
    }
}
