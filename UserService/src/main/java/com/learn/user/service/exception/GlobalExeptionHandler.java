package com.learn.user.service.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.learn.user.service.payload.ApiResponse;

@RestControllerAdvice
public class GlobalExeptionHandler {

	@ExceptionHandler(ResourceNotFoundexception.class)
	public ResponseEntity<Map<String, Object>> notFoundHandler(ResourceNotFoundexception ex){
		Map map = new HashMap();
		map.put("message", ex.getMessage());
		map.put("success",false);
		map.put("status",HttpStatus.NOT_FOUND);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
		
	}
	
	
}
