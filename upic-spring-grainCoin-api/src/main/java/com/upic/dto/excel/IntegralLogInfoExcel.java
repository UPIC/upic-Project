package com.upic.dto.excel;

import com.upic.enums.IntegralLogStatusEnum;
import com.upic.enums.IntegralLogTypeEnum;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class IntegralLogInfoExcel implements Serializable {
	String studentNum;

    String projectNum;

    private String event; //事件 json

    private String integral; //分数

    private IntegralLogTypeEnum type; //类型

    private IntegralLogStatusEnum status; //状态

    private String student; //学生姓名

    private String clazz;

    private String college;

    private List<String> integralLogPic; //图片
    
    private int version;

    private Date creatTime;

    private String field1;

    private String field2;

    private String field3;

    private String field4;

    private String field5;

    private Date addTime;

    private String projectName;

    private String projectCategory;

    private String collegeOtherName;

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

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getIntegral() {
		return integral;
	}

	public void setIntegral(String integral) {
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

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
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

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectCategory() {
		return projectCategory;
	}

	public void setProjectCategory(String projectCategory) {
		this.projectCategory = projectCategory;
	}

	public String getCollegeOtherName() {
		return collegeOtherName;
	}

	public void setCollegeOtherName(String collegeOtherName) {
		this.collegeOtherName = collegeOtherName;
	}

	public IntegralLogInfoExcel() {
		super();
	}

}