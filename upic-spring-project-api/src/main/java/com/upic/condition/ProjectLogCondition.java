package com.upic.condition;

import com.upic.common.base.condition.BaseCondition;

/**
 * Created by zhubuqing on 2017/9/6.
 */
public class ProjectLogCondition extends BaseCondition {
    private String operation; //操作内容

    private String operatorName; //操作人

    private Long projectId;

    private String operatorNum; //操作人编号

    public ProjectLogCondition() {
        super();
    }

    public ProjectLogCondition(String operation, String operatorName, Long projectId, String operatorNum) {
        this.operation = operation;
        this.operatorName = operatorName;
        this.projectId = projectId;
        this.operatorNum = operatorNum;
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

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getOperatorNum() {
        return operatorNum;
    }

    public void setOperatorNum(String operatorNum) {
        this.operatorNum = operatorNum;
    }
}
