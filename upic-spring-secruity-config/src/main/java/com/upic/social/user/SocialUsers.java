package com.upic.social.user;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.social.security.SocialUserDetails;

import com.upic.dto.ResourceInfo;

public class SocialUsers extends User implements SocialUserDetails {

	public SocialUsers(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}

	//实际存储用户姓名
	private String userNum;
	private String college;

	private String major;
//所有审批状态
	private List<String> statusList;
	//所有项目类别
	private List<String> projectCategoryList;
	//所有菜单
	private List<ResourceInfo> resourceList;
	
	private String rank;

	//部门别名
	private String collegeAli;

	public SocialUsers(String username, String password, Collection<? extends GrantedAuthority> authorities,
			String userNum, String college, String major, List<String> statusList, List<String> projectCategoryList,
			List<ResourceInfo> resourceList, String rank, String collegeAli) {
		super(username, password, authorities);
		this.userNum = userNum;
		this.college = college;
		this.major = major;
		this.statusList = statusList;
		this.projectCategoryList = projectCategoryList;
		this.resourceList = resourceList;
		this.rank = rank;
		this.collegeAli = collegeAli;
	}

	public String getUserNum() {
		return userNum;
	}

	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public List<String> getStatusList() {
		return statusList;
	}

	public void setStatusList(List<String> statusList) {
		this.statusList = statusList;
	}

	public List<String> getProjectCategoryList() {
		return projectCategoryList;
	}

	public void setProjectCategoryList(List<String> projectCategoryList) {
		this.projectCategoryList = projectCategoryList;
	}

	public List<ResourceInfo> getResourceList() {
		return resourceList;
	}

	public void setResourceList(List<ResourceInfo> resourceList) {
		this.resourceList = resourceList;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String getCollegeAli() {
		return collegeAli;
	}

	public void setCollegeAli(String collegeAli) {
		this.collegeAli = collegeAli;
	}

	@Override
	public String getUserId() {
		return super.getUsername();
	}
	
}
