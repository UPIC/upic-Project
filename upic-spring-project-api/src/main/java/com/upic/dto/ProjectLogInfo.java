package com.upic.dto;

import com.upic.common.base.dto.BaseInfo;

/**
 * Created by zhubuqing on 2017/8/4.
 */
public class ProjectLogInfo extends BaseInfo {
    private String operation; //操作内容

    private String operatorName; //操作人

    private String operatorNum; //操作人编号

    private long projectId;

    public ProjectLogInfo() {
        super();
    }

    public ProjectLogInfo(String operation, String operatorName, String operatorNum, long projectId) {
        this.operation = operation;
        this.operatorName = operatorName;
        this.operatorNum = operatorNum;
        this.projectId = projectId;
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

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    @Override
    public String toString() {
        return "ProjectLogInfo{" +
                "operation='" + operation + '\'' +
                ", operatorName='" + operatorName + '\'' +
                ", operatorNum='" + operatorNum + '\'' +
                ", projectId=" + projectId +
                '}';
    }
}
