package com.upic.dto;

import com.upic.common.base.dto.BaseInfo;
import com.upic.enums.MailLogTypeEnum;

import java.util.Date;

/**
 * Created by zhubuqing on 2017/8/4.
 */
public class MailLogInfo extends BaseInfo {
    private String operator; //操作人

    private String operatorNum; //操作人编号

    private String operation; //操作内容

    private MailLogTypeEnum type; //操作类型

    private MailInfo mail;

    public MailLogInfo() {
        super();
    }

    public MailLogInfo(Long id, Date creatTime, String field1, String field2, String field3, String field4, String field5, String operator, String operatorNum, String operation, MailLogTypeEnum type, MailInfo mail) {
        this.operator = operator;
        this.operatorNum = operatorNum;
        this.operation = operation;
        this.type = type;
        this.mail = mail;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getOperatorNum() {
        return operatorNum;
    }

    public void setOperatorNum(String operatorNum) {
        this.operatorNum = operatorNum;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public MailLogTypeEnum getType() {
        return type;
    }

    public void setType(MailLogTypeEnum type) {
        this.type = type;
    }

    public MailInfo getMail() {
        return mail;
    }

    public void setMail(MailInfo mail) {
        this.mail = mail;
    }

    @Override
    public String toString() {
        return "MailLogInfo{" +
                "operator='" + operator + '\'' +
                ", operatorNum='" + operatorNum + '\'' +
                ", operation='" + operation + '\'' +
                ", type=" + type +
                ", mail=" + mail +
                '}';
    }
}
