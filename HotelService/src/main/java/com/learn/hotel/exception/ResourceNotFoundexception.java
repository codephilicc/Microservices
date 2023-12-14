package com.learn.hotel.exception;

public class ResourceNotFoundexception extends RuntimeException{

	public ResourceNotFoundexception(String s) {
		super(s);
	}
	
	public ResourceNotFoundexception() {
		super("Resource not found !!");
	}
	
	
}
