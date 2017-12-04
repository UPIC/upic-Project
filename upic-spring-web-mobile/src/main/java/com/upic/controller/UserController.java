package com.upic.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.web.ProviderSignInAttempt;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.security.SocialUser;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.neusoft.education.tp.sso.client.filter.CASFilterRequestWrapper;
import com.upic.common.utils.redis.WebRequestRedisService;
import com.upic.dto.StudentInfo;
import com.upic.dto.UserInfo;
//import com.upic.po.Student;
//import com.upic.repository.StudentRspoitory;
import com.upic.service.UserService;

@RestController
public class UserController {
	@Autowired
	private WebRequestRedisService redisService;

	@Autowired
	private ProviderSignInUtils providerSignInUtils;


	@Autowired
	private UserService userService;

	@RequestMapping("/registUser")
	public String createUser(WebRequest request,StudentInfo info) {
		providerSignInUtils.doPostSignUp(info.getStuNum(), request);
		UserInfo s=new UserInfo();
		s.setUserNum(info.getStuNum());
		s.setPassword(info.getPassword());
		userService.addUser(s);
		return "/login.html";
	}

	@RequestMapping("/getUserInfo")
	public StudentInfo readyRegist() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(authentication);
		if(authentication != null) {
			System.out.println(authentication.getPrincipal());
		}
		SocialUser so=(SocialUser) authentication.getPrincipal();
		StudentInfo s=new StudentInfo();
		s.setStuNum(so.getUsername());
		return  s;
	}

	@RequestMapping("/cas")
	public String createUsers(WebRequest request,HttpServletRequest requests,HttpSession session,RedirectAttributes  model,String sessionId) {
		CASFilterRequestWrapper reqWrapper=new CASFilterRequestWrapper(requests);
		String userID = reqWrapper.getRemoteUser();
		ProviderSignInAttempt webRequestSer = (ProviderSignInAttempt) redisService.get(sessionId);
		request.setAttribute(ProviderSignInAttempt.SESSION_ATTRIBUTE, webRequestSer, RequestAttributes.SCOPE_SESSION);
//		System.out.println(webRequestSer);
		if(userID==null) {
			System.out.println("获取失败");
		}
		providerSignInUtils.doPostSignUp(userID, request);
		model.addAttribute("stuId", userID);
		return "redirect:/casget";
	}

	@RequestMapping("/casgo")
	public String saveSession(WebRequest request,HttpServletRequest r,HttpSession session) {
		String sessionId = request.getSessionId();
		ProviderSignInAttempt attribute = (ProviderSignInAttempt) request.getAttribute(ProviderSignInAttempt.SESSION_ATTRIBUTE, RequestAttributes.SCOPE_SESSION);
		redisService.put(sessionId, attribute, 60*20);
		return "forward:/cas?sessionId="+sessionId;
	}
}
