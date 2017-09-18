//package com.upic.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.social.connect.Connection;
//import org.springframework.social.connect.web.ProviderSignInUtils;
//import org.springframework.social.security.SocialUser;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.context.request.WebRequest;
//
//import com.upic.dto.StudentInfo;
////import com.upic.po.Student;
////import com.upic.repository.StudentRspoitory;
//
////@RestController
//public class UserController {
//
//	@Autowired
//	private ProviderSignInUtils providerSignInUtils;
//	
//	
////	@Autowired
////	private StudentRspoitory rspoitory;
//	@RequestMapping("/registUser")
//	public String createUser(WebRequest request,StudentInfo info) {
//		providerSignInUtils.doPostSignUp(info.getStuNum(), request);
////		Student s=new Student();
////		s.setStuNum(info.getStuNum());
////		s.setPassword(info.getPassword());
////		rspoitory.save(s);
//		return "/login.html";
//	}
//	
//	@RequestMapping("/getUserInfo")
//	public StudentInfo readyRegist() {
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		System.out.println(authentication);
//		if(authentication != null) {
//			System.out.println(authentication.getPrincipal());
//		}
//		SocialUser so=(SocialUser) authentication.getPrincipal();
//		StudentInfo s=new StudentInfo();
//		s.setStuNum(so.getUsername());
//		return  s;
//	}
//}
