package com.upic.controller;

import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSONArray;
import com.upic.common.document.excel.ExcelDocument;
import com.upic.dto.UserInfo;
import com.upic.dto.excel.IntegralLogInfoExcel;
import com.upic.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	protected static final Logger LOGGER = LoggerFactory.getLogger(StudetAllController.class);
	@Autowired
	private UserService userService;

	@PostMapping("/batchAddStudent")
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
			String[] baseModels = new String[] {};
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
}
