package com.upic.social.user;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.social.security.SocialUserDetails;

public class SocialUsers extends User implements SocialUserDetails {

	private String userNum;
	private String college;

	private String major;

	public SocialUsers(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
	}

	public SocialUsers(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}

	public SocialUsers(String username, String password, Collection<? extends GrantedAuthority> authorities,
			String userNum, String college, String major) {
		super(username, password, authorities);
		this.userNum = userNum;
		this.college = college;
		this.major = major;
	}

	@Override
	public String getUserId() {
		return getUsername();
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

	public String getUserNum() {
		return userNum;
	}

	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}

}
