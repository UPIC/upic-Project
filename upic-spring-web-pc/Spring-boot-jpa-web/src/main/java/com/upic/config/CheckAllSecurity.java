package com.upic.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component("checkAllSecurity")
public class CheckAllSecurity {

	
	public boolean check(Authentication authentication,HttpServletRequest request){
		if(authentication==null){
			return false;
		}
		Object principal = authentication.getPrincipal();
		if(principal != null && principal instanceof UserDetails) {
			System.out.println(((UserDetails)principal).getAuthorities());
			return true;
		}
		return false;
	}
}
