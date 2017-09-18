package com.upic.po;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.upic.po.base.BaseEntity;

@Entity
public class StudentCourse extends BaseEntity{

	@ManyToOne
	private Student student;
	@ManyToOne
	private Course course;
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public StudentCourse(Long id, int version, Date creatTime, Student student, Course course) {
		super(id, version, creatTime);
		this.student = student;
		this.course = course;
	}
	public StudentCourse() {
		super();
	}
	
}
