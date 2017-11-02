package com.upic.security.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component("checkAllSecurity")
public class CheckAllSecurity {

	private final List<String> staticUrl=Arrays.asList("/metroWeb/css/","/metroWeb/js/","/metroWeb/img/","/metroWeb/others/");
	
	public boolean check(Authentication authentication, HttpServletRequest request) {
		if (authentication == null) {
			return false;
		}
		if (authentication instanceof AnonymousAuthenticationToken) {
			return false;
		}
		Object principal = authentication.getPrincipal();

		if (principal != null && principal instanceof UserDetails) {
			Collection<? extends GrantedAuthority> authorities = ((UserDetails) principal).getAuthorities();
			for (GrantedAuthority g : authorities) {
			
				if (request.getRequestURI().startsWith(g.getAuthority())) {
					return true;
				}
			}
			for (String g : staticUrl) {
			if(request.getRequestURI().startsWith(g)) {
				return true;
			}
			System.out.println("sorry!");
			}
		}

		return false;
	}
}
