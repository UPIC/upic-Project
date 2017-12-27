package com.upic.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.web.ProviderSignInAttempt;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.security.SocialUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.neusoft.education.tp.sso.client.filter.CASFilterRequestWrapper;
import com.upic.common.utils.redis.WebRequestRedisService;
import com.upic.common.utils.redis.service.IRedisService;
import com.upic.dto.StudentInfo;
import com.upic.dto.UserInfo;
import com.upic.enums.UserTypeEnum;
//import com.upic.po.Student;
//import com.upic.repository.StudentRspoitory;
import com.upic.service.UserService;
import com.upic.social.user.SocialUsers;
import com.upic.utils.UserUtils;

@Controller
public class UserController {
	// @Autowired
	// private WebRequestRedisService redisService;

	@Autowired
	private IRedisService redisService;

	@Autowired
	private ProviderSignInUtils providerSignInUtils;

	@Autowired
	private UserService userService;

	@RequestMapping("/registUser")
	public String createUser(WebRequest request, StudentInfo info) {
		providerSignInUtils.doPostSignUp(info.getStuNum(), request);
		UserInfo s = new UserInfo();
		s.setUserNum(info.getStuNum());
		s.setPassword(info.getPassword());
		userService.addUser(s);
		return "/login.html";
	}

	@RequestMapping("/getUserInfo")
	public StudentInfo readyRegist() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(authentication);
		if (authentication != null) {
			System.out.println(authentication.getPrincipal());
		}
		SocialUser so = (SocialUser) authentication.getPrincipal();
		StudentInfo s = new StudentInfo();
		s.setStuNum(so.getUsername());
		return s;
	}

	@RequestMapping("/casgo")
	public void createUsers(WebRequest request, HttpServletRequest requests, HttpServletResponse response,
			HttpSession session, RedirectAttributes model, String sessionId) {
		CASFilterRequestWrapper reqWrapper = new CASFilterRequestWrapper(requests);
		String userID = reqWrapper.getRemoteUser();
		ProviderSignInAttempt webRequestSer = (ProviderSignInAttempt) redisService.getObj("TEST_REDIS_KEY", sessionId);
		request.setAttribute(ProviderSignInAttempt.SESSION_ATTRIBUTE, webRequestSer, RequestAttributes.SCOPE_SESSION);
		// System.out.println(webRequestSer);
		if (userID == null) {
			System.out.println("获取失败");
		}
		providerSignInUtils.doPostSignUp(userID, request);
		String payUrl = "/auth";// POST提交地址
		String user = "user";// 如有多个，以此类推。
		String pass = "pass";

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out;
		try {
			out = response.getWriter();
			out.println("<form name='authSubmit' method='post'  action='" + payUrl + "' >");
			out.println("<input type='hidden' name='" + user + "' value='" + userID + "'>"); // 如有多个，则写多个hidden即可
			out.println("<input type='hidden' name='" + pass + "' value='" + userID + "'>");
			out.println("</form>");
			out.println("<script>");
			out.println("  document.authSubmit.submit()");
			out.println("</script>");
		} catch (IOException e) {
		}

	}

	@RequestMapping("/cas")
	public String saveSession(WebRequest request, HttpServletRequest r, HttpSession session) {
		String sessionId = request.getSessionId();
		ProviderSignInAttempt attribute = (ProviderSignInAttempt) request
				.getAttribute(ProviderSignInAttempt.SESSION_ATTRIBUTE, RequestAttributes.SCOPE_SESSION);
		redisService.put("TEST_REDIS_KEY", sessionId, attribute, 60 * 20);
		return "forward:/casgo?sessionId=" + sessionId;
	}

	@RequestMapping("/upiclogin")
	public String login() {
		SocialUsers user = null;
		try {
			// 如果是游客就会报异常
			user = UserUtils.getUser();
		} catch (Exception e) {
			return "/auth/weixin";
		}
		if (user.getUserId().length() > 0) {
			return "/student/st-main.html";
		}
		return "/teacher/teacher-main.html";
	}
}
