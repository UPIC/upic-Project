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

import com.upic.condition.OperatorRoleCondition;
import com.upic.dto.UserInfo;
import com.upic.enums.UserTypeEnum;
import com.upic.service.OperatorRoleService;
import com.upic.service.ProjectCategoryService;
import com.upic.service.RoleCheckStatusService;
import com.upic.service.RoleResourceService;
import com.upic.service.RoleService;
import com.upic.service.UserService;
import com.upic.social.user.SocialUsers;


@Component
public  class MyUserDetailsService implements UserDetailsService, SocialUserDetailsService {
	@Autowired
	private UserService userService;

	@Autowired
	private RoleCheckStatusService roleCheckStatusService;
	
	@Autowired
	private ProjectCategoryService projectCategoryService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private OperatorRoleService operatorRoleService;
	
	@Autowired
	private RoleResourceService roleResourceService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return loadUserByUserId(username);
	}

	@Override
	public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
		UserInfo userInfo = userService.getUserByUserNum(userId);
		OperatorRoleCondition operatorRoleCondition = new OperatorRoleCondition();
		if (userInfo == null) {
			throw new UsernameNotFoundException("用户名不存在，请联系管理员！");
		}
		List<GrantedAuthority> createAuthorityList=null;
		if(userInfo.getType().equals(UserTypeEnum.STUDENT)) {
			// 获取页请求页面权限
			createAuthorityList = AuthorityUtils.createAuthorityList("/*");
			//获取菜单
		}else if(userInfo.getType().equals(UserTypeEnum.TEACHER)) {
			//加载所有角色
//			operatorRoleCondition.se
			//找出所有角色ID
//			operatorRoleService.searchOperatorRole(, pageable)
			//根据角色ID找出所有菜单 foreach
//			roleResourceService.searchRoleResource(roleResourceCondition, pageable);
			//根据角色ID获取审批状态
//			roleCheckStatusService.getCheckStatusEnumName(角色ID);
			//根据角色中的部门别名获取所有类别
//			projectCategoryService.getCategoryNameBySubordinateSectorOtherName(部门别名)
		}
		
		
		
		return new SocialUsers(userInfo.getUsername(), userInfo.getPassword(), createAuthorityList,
				userInfo.getUserNum(), userInfo.getCollege(), userInfo.getMajor());

	}

}
