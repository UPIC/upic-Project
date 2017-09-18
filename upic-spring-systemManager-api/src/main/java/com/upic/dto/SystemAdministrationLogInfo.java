package com.upic.dto;

import com.upic.common.base.dto.BaseInfo;
import com.upic.enums.SystemAdministrationLogTypeEnum;

/**
 * Created by zhubuqing on 2017/8/4.
 */
public class SystemAdministrationLogInfo extends BaseInfo {
    private String operation; //操作内容

    private String operatorName; //操作人

    private String operatorNum; //操作人编号

    private SystemAdministrationLogTypeEnum type; //操作类型

    public SystemAdministrationLogInfo() {
        super();
    }

    public SystemAdministrationLogInfo(String operation, String operatorName, String operatorNum, SystemAdministrationLogTypeEnum type) {
        super();
        this.operation = operation;
        this.operatorName = operatorName;
        this.operatorNum = operatorNum;
        this.type = type;
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

    public SystemAdministrationLogTypeEnum getType() {
        return type;
    }

    public void setType(SystemAdministrationLogTypeEnum type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "SystemAdministrationLogInfo{" +
                "operation='" + operation + '\'' +
                ", operatorName='" + operatorName + '\'' +
                ", operatorNum='" + operatorNum + '\'' +
                ", type=" + type +
                '}';
    }
}
