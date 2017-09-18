package com.upic.po;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

//import org.hibernate.validator.constraints.Email;

import com.upic.enums.Sex;
import com.upic.po.base.BaseEntity;
//,
//@NamedEntityGraph(name = "Student.fetch1", attributeNodes = { @NamedAttributeNode("course") }) 
@Entity
@NamedEntityGraphs(value = {
		@NamedEntityGraph(name = "Student.fetch", attributeNodes = { @NamedAttributeNode("course"),
				 })})
public class Student extends BaseEntity {

	@Column(unique = true, nullable = false)
	private String stuNum;
	@Enumerated(EnumType.STRING)
	private Sex sex;
	@Temporal(TemporalType.DATE)
	private Date birthday;

	private String password;
	
	@ElementCollection
	private List<String> hobby;

	@Embedded
	private Address address;

//	@Email
	private String email;
	
	@OneToMany(mappedBy = "student")
	private List<StudentCourse> course;

	public String getStuNum() {
		return stuNum;
	}

	public void setStuNum(String stuNum) {
		this.stuNum = stuNum;
	}

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Student(Long id, int version, Date creatTime, String stuNum, Sex sex, Date birthday) {
		super(id, version, creatTime);
		this.stuNum = stuNum;
		this.sex = sex;
		this.birthday = birthday;
	}

	public Student(Long id, int version, Date creatTime) {
		super(id, version, creatTime);
	}

	public List<String> getHobby() {
		return hobby;
	}

	public void setHobby(List<String> hobby) {
		this.hobby = hobby;
	}

	public Student() {
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<StudentCourse> getCourse() {
		return course;
	}

	public void setCourse(List<StudentCourse> course) {
		this.course = course;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
