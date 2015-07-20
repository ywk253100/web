package com.web.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.controller.validator.UserValidator;
import com.web.model.User;
import com.web.service.UserService;
import com.web.bean.Error;

@Controller
@RequestMapping(value = "/user", produces = "application/json;charset=utf-8")
public class TestController extends BaseController{
	@Autowired
	private UserValidator userValidator;
	@Autowired
	private UserService userService;
	
	
	@RequestMapping(value = "/add")
	@ResponseBody
	public String add(User user, BindingResult result){
		userValidator.validate(user, result);
		if(result.hasErrors()){
			return new Error(result).toJson();
		}
		if(userService.isExist("username", user.getUsername())){
			return new Error("error.username.exist", "The username already exists.").toJson();
		}
		return userService.save(user);
	}
	
	@RequestMapping("/login")
	@ResponseBody
	public String login(String username, String password, boolean rememberMe){
		UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
		Subject currentUser = SecurityUtils.getSubject();
		try {
			currentUser.login(token);
		} catch (Exception e) {
			return new Error("error.login.error", "Incorrect username or password.").toJson();
		}
		return "";
	}
	
	@RequestMapping("/logout")
	@ResponseBody
	public String logout(){
		Subject currentUser = SecurityUtils.getSubject();
		currentUser.logout();
		return "";
	}
	
	@RequiresRoles("common_user")
	@RequestMapping("/update")
	@ResponseBody
	public String update(){
		return "ddd";
	}
}