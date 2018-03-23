package com.upic.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONArray;
import com.upic.common.document.excel.ExcelDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.security.SocialUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSONArray;
import com.neusoft.education.tp.sso.client.filter.CASFilterRequestWrapper;
import com.upic.common.document.excel.ExcelDocument;
import com.upic.common.utils.redis.WebRequestRedisService;
import com.upic.dto.StudentInfo;
import com.upic.dto.UserInfo;
import com.upic.service.UserService;
import com.upic.social.user.SocialUsers;
import com.upic.utils.UserUtils;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
public class UserController {

    protected static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private WebRequestRedisService redisService;

    @Autowired
    private ProviderSignInUtils providerSignInUtils;

    @Autowired
    private UserService userService;

    @PostMapping("/user/batchAddStudent")
    @ResponseBody
    public String batchAddStudent(HttpServletRequest request, String baseModel) {
        List<Object> list = null;
        try {
            // 转型为MultipartHttpRequest：
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            // 获得文件：
            MultipartFile inputFile = multipartRequest.getFile("inputFile");
            // 获得文件名：
            String filename = inputFile.getOriginalFilename();
            // 获得输入流：
            // InputStream input = file.getInputStream();
            String[] baseModels = new String[]{};
            List<String> parseArray = JSONArray.parseArray(baseModel, String.class);
            baseModels = parseArray.toArray(baseModels);
            InputStream inputStream = inputFile.getInputStream();
            if (inputStream == null) {
                throw new Exception("文件为空");
            }
            list = ExcelDocument.upload(inputStream, baseModels, UserInfo.class, filename);
            // integralLogService.saveAll(list);
            userService.batchAddUser(list);
        } catch (Exception e) {
            LOGGER.info("batchAddStudent:" + e.getMessage());
            return null;
        }
        return "SUCCESS";
    }

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

    @RequestMapping("/cas")
    public void createUsers(HttpServletRequest request, HttpServletResponse response, String sessionId) {
        CASFilterRequestWrapper reqWrapper = new CASFilterRequestWrapper(request);
        String userID = reqWrapper.getRemoteUser();
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

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        Enumeration attributeNames = session.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String attrName = attributeNames.nextElement().toString();
            session.setAttribute(attrName, null);
            session.removeAttribute(attrName);
        }
        return "/cas";
    }

    @PostMapping("/login")
    public void login(HttpServletRequest request, HttpServletResponse response, String user, String pass) {
        PrintWriter out;
        try {
            response.setContentType("text/html;charset=utf-8");
            out = response.getWriter();
            UserInfo userByUserNum = userService.getUserByUserNum(user);
            if (userByUserNum == null) {
                out.println("<script>");
                out.println(" alert('账号不存在')");
                out.println("window.location.href='/upiclogin' ");
                out.println("</script>");
                return;
            }
            BCryptPasswordEncoder b = new BCryptPasswordEncoder();
            if (!b.matches(pass, userByUserNum.getPassword())) {
                out.println("<script>");
                out.println(" alert('密码不正确')");
                out.println("window.location.href='/upiclogin' ");
                out.println("</script>");
                return;
            }
            String payUrl = "/auth";// POST提交地址
            String users = "user";// 如有多个，以此类推。
            String passs = "pass";


            out.println("<form name='authSubmit' method='post'  action='" + payUrl + "' >");
            out.println("<input type='hidden' name='" + users + "' value='" + user + "'>"); // 如有多个，则写多个hidden即可
            out.println("<input type='hidden' name='" + passs + "' value='" + user + "'>");
            out.println("</form>");
            out.println("<script>");
            out.println("  document.authSubmit.submit()");
            out.println("</script>");
        } catch (Exception e) {

        }
    }


    @RequestMapping("/upiclogin")
    public String login() {
        try {
            //如果是游客就会报异常
            UserUtils.getUser();
        } catch (Exception e) {
//			return "/cas";
            return "/metroWeb/html/login.html";
        }
        return "/metroWeb/html/t_index/index.html";
    }
}
