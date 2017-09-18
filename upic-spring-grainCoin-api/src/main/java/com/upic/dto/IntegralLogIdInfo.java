package com.upic.dto;

import java.io.Serializable;

public class IntegralLogIdInfo implements Serializable {
    String studentNum;

    String projectNum;

    public IntegralLogIdInfo() {
    }

    public IntegralLogIdInfo(String studentNum, String projectNum) {
        this.studentNum = studentNum;
        this.projectNum = projectNum;
    }

    public String getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(String studentNum) {
        this.studentNum = studentNum;
    }

    public String getProjectNum() {
        return projectNum;
    }

    public void setProjectNum(String projectNum) {
        this.projectNum = projectNum;
    }

    @Override
    public String toString() {
        return "IntegralLogId{" +
                "studentNum='" + studentNum + '\'' +
                ", projectNum='" + projectNum + '\'' +
                '}';
    }
}