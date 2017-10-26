package com.upic.security.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;

import com.upic.dto.UserInfo;
import com.upic.service.UserService;
import com.upic.social.user.SocialUsers;


@Component
public  class MyUserDetailsService implements UserDetailsService, SocialUserDetailsService {
	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return loadUserByUserId(username);
	}

	@Override
	public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
		UserInfo userInfo = userService.getUserByUserNum(userId);
		if (userInfo == null) {
			throw new UsernameNotFoundException("用户名不存在，请联系管理员！");
		}
		// 获取页请求页面权限
		List<GrantedAuthority> createAuthorityList = AuthorityUtils.createAuthorityList("/*");
		return new SocialUsers(userInfo.getUsername(), userInfo.getPassword(), createAuthorityList,
				userInfo.getUserNum(), userInfo.getCollege(), userInfo.getMajor());

	}

}
