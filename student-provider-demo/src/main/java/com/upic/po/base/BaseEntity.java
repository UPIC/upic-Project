package com.upic.po.base;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
@MappedSuperclass
public class BaseEntity implements Serializable{
	private static final long serialVersionUID = 6998031580391808779L;
	@Id
	@GeneratedValue
	private Long id;
	@Version
	private int version;
	@Temporal(TemporalType.TIME)
	private Date creatTime;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public BaseEntity(Long id, int version, Date creatTime) {
		super();
		this.id = id;
		this.version = version;
		this.creatTime = creatTime;
	}
	public BaseEntity() {
	}
}
