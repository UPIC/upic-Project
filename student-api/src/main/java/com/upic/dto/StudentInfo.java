package com.upic.dto;

import java.io.Serializable;
import java.util.Date;


public class StudentInfo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public interface StudentListView{}
	public interface StudentView extends StudentListView{}
	
	private Long id;
//	@NotBlank
//	@ApiModelProperty("学生学号")
	private String stuNum;
	private String classNum;
	private String password;
	private Date createTime;
//	@JsonView(StudentListView.class)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
//	@JsonView(StudentListView.class)
	public String getStuNum() {
		return stuNum;
	}
	public void setStuNum(String stuNum) {
		this.stuNum = stuNum;
	}
	public String getClassNum() {
		return classNum;
	}
	public void setClassNum(String classNum) {
		this.classNum = classNum;
	}
	public StudentInfo(Long id, String stuNum, String classNum) {
		super();
		this.id = id;
		this.stuNum = stuNum;
		this.classNum = classNum;
	}
	public StudentInfo() {
		super();
	}
//	@JsonView(StudentView.class)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		return "StudentInfo [id=" + id + ", stuNum=" + stuNum + ", classNum=" + classNum + ", password=" + password
				+ ", createTime=" + createTime + "]";
	}
	
}
