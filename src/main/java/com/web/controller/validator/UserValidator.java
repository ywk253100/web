package com.web.controller.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator{

	public boolean supports(Class<?> cClass) {
		return UserValidator.class.equals(cClass);
	}

	public void validate(Object object, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "username", "error.username.empty", "Please specify a username.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.password.empty", "Please specify a password.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "role", "error.role.empty", "Please specify a role.");
	}
}
