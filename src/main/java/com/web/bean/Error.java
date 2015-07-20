package com.web.bean;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

import com.alibaba.fastjson.JSON;

public class Error {

	List<ErrorItem> errors = new ArrayList<ErrorItem>();

	public Error(){
		
	}
	
	public Error(String code, String message) {
		errors.add(new ErrorItem(code, message));
	}

	public Error(Errors errs) {
		List<ObjectError> objectErrors= errs.getAllErrors();
		if(objectErrors != null){
			for(ObjectError error : objectErrors){
				errors.add(new ErrorItem(error.getCode(), error.getDefaultMessage()));
			}
		}
	}
	
	public String toJson(){
		return JSON.toJSONString(this);
	}
	
	public List<ErrorItem> getErrors() {
		return errors;
	}

	public void setErrors(List<ErrorItem> errors) {
		this.errors = errors;
	}
}

class ErrorItem{
	private String code;
	private String message;
	
	public ErrorItem(String code, String message){
		this.code = code;
		this.message = message;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
