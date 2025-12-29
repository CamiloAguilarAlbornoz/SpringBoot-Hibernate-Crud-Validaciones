package com.camilo.curso.springboot.app.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.camilo.curso.springboot.app.services.UserService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class ExistByUserNameValidation implements ConstraintValidator<ExistByUserName, String> {

	@Autowired
	private UserService userService;
	
	@Override
	public boolean isValid(String userName, ConstraintValidatorContext context) {
		boolean isValid = false;
		if (userService != null) {
			isValid = !userService.existsByUserName(userName);
		}
		System.err.println(isValid);
		return isValid;
	}
}
