package com.upic.support;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionSupport {

	@ResponseStatus
	@ExceptionHandler(Exception.class)
	public Map<String,Object> handlerException(RuntimeException exception){
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("result", "fail");
		map.put("errMsg", exception.getMessage());
		return map;
	}
}
