package com.upic.dto;

import com.upic.enums.IntegralLogStatusEnum;
import com.upic.enums.IntegralLogTypeEnum;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by zhubuqing on 2017/8/5.
 */
public class IntegralLogInfo implements Serializable {
    private IntegralLogIdInfo integralLogId;

    private String event; //事件 json

    private double integral; //分数

    private IntegralLogTypeEnum type; //类型

    private IntegralLogStatusEnum status; //状态

    private String student; //学生姓名

    private String clazz;

    private String college;

    private List<String> integralLogPic; //图片

    private Date creatTime;

    private String field1;

    private String field2;

    private String field3;

    private String field4;

    private String field5;

    public IntegralLogInfo() {
    }

    public IntegralLogInfo(IntegralLogIdInfo integralLogId, String event, double integral, IntegralLogTypeEnum type, IntegralLogStatusEnum status, String student, String clazz, String college, List<String> integralLogPic, Date creatTime, String field1, String field2, String field3, String field4, String field5) {
        this.integralLogId = integralLogId;
        this.event = event;
        this.integral = integral;
        this.type = type;
        this.status = status;
        this.student = student;
        this.clazz = clazz;
        this.college = college;
        this.integralLogPic = integralLogPic;
        this.creatTime = creatTime;
        this.field1 = field1;
        this.field2 = field2;
        this.field3 = field3;
        this.field4 = field4;
        this.field5 = field5;
    }

    public IntegralLogIdInfo getIntegralLogId() {
        return integralLogId;
    }

    public void setIntegralLogId(IntegralLogIdInfo integralLogId) {
        this.integralLogId = integralLogId;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public double getIntegral() {
        return integral;
    }

    public void setIntegral(double integral) {
        this.integral = integral;
    }

    public IntegralLogTypeEnum getType() {
        return type;
    }

    public void setType(IntegralLogTypeEnum type) {
        this.type = type;
    }

    public IntegralLogStatusEnum getStatus() {
        return status;
    }

    public void setStatus(IntegralLogStatusEnum status) {
        this.status = status;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public List<String> getIntegralLogPic() {
        return integralLogPic;
    }

    public void setIntegralLogPic(List<String> integralLogPic) {
        this.integralLogPic = integralLogPic;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    public String getField2() {
        return field2;
    }

    public void setField2(String field2) {
        this.field2 = field2;
    }

    public String getField3() {
        return field3;
    }

    public void setField3(String field3) {
        this.field3 = field3;
    }

    public String getField4() {
        return field4;
    }

    public void setField4(String field4) {
        this.field4 = field4;
    }

    public String getField5() {
        return field5;
    }

    public void setField5(String field5) {
        this.field5 = field5;
    }

    @Override
    public String toString() {
        return "IntegralLogInfo{" +
                "integralLogId=" + integralLogId +
                ", event='" + event + '\'' +
                ", integral=" + integral +
                ", type=" + type +
                ", status=" + status +
                ", student='" + student + '\'' +
                ", clazz='" + clazz + '\'' +
                ", college='" + college + '\'' +
                ", creatTime=" + creatTime +
                ", field1='" + field1 + '\'' +
                ", field2='" + field2 + '\'' +
                ", field3='" + field3 + '\'' +
                ", field4='" + field4 + '\'' +
                ", field5='" + field5 + '\'' +
                '}';
    }
}