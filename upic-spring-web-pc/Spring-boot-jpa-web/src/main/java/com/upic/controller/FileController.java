package com.upic.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.upic.dto.FileInfo;

@RestController
@RequestMapping("/file")
public class FileController {

	@PostMapping("/upload")
	public FileInfo update(MultipartFile file) throws IllegalStateException, IOException {
		System.out.println(file.getContentType());
		System.out.println(file.getName());
		System.out.println(file.getOriginalFilename());
		System.out.println(file.getSize());
		String path = "D:/spring boot/Spring-boot-jpa-web/src/main/resources/file";
		String[] split = file.getOriginalFilename().split("//.");
		File f = new File(path, new Date().getTime() + "." + split[split.length - 1]);
		file.transferTo(f);
		return new FileInfo(path);
	}

	@GetMapping("/download")
	public void download(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String filePath = "D:/spring boot/Spring-boot-jpa-web/src/main/resources/file/1500534039688.txt";
		try (InputStream inputStream = new FileInputStream(filePath);
				OutputStream outputStream = response.getOutputStream();) {

			response.setContentType("application/x-download");
			response.addHeader("Content-Disposition", "attachment;filename=test.txt");

			IOUtils.copy(inputStream, outputStream);
			outputStream.flush();
		}
	}
	
}
