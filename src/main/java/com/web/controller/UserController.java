package com.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.util.SystemConfigUtil;

@Controller
@RequestMapping(produces = "application/json;charset=utf-8")
public class UserController extends BaseController{
	
	@RequestMapping(value = "/test")
	@ResponseBody
	public String test(){
		System.out.println(SystemConfigUtil.getName());
		return "";
	}
}