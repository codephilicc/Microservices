//package com.learn.user.service.exception;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//import com.learn.user.service.payload.ApiResponse;
//
//@RestControllerAdvice
//public class GlobalExeptionHandler {
//
//	@ExceptionHandler(ResourceNotFoundexception.class)
//	public ResponseEntity<ApiResponse> handlerResourceNotFoundEception(ResourceNotFoundexception ex){
//		String message = ex.getMessage();
//		ApiResponse response = ApiResponse.builder().message(message).success(true).status(HttpStatus.NOT_FOUND).build();
//		return new ResponseEntity<ApiResponse>(response,HttpStatus.NOT_FOUND);
//		
//	}
//	
//	
//}
