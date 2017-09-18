package com.upic.po;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.upic.po.base.BaseEntity;

@Entity
public class Course extends BaseEntity{
	private String courseName;
	@Temporal(TemporalType.DATE)
	private Date startTime;
	@Temporal(TemporalType.DATE)
	private Date endTime;
	
	@OneToMany(mappedBy="course")
	private List<StudentCourse> studentCourse;
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Course(Long id, int version, Date creatTime, String courseName, Date startTime, Date endTime) {
		super(id, version, creatTime);
		this.courseName = courseName;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	public Course(Long id, int version, Date creatTime) {
		super(id, version, creatTime);
	}
	public Course() {
		super();
	}

}
