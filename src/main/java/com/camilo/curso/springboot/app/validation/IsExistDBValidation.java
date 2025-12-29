package com.camilo.curso.springboot.app.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.camilo.curso.springboot.app.services.ProductService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class IsExistDBValidation implements ConstraintValidator<IsExistDB, String> {

	@Autowired
	private ProductService productService;
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return productService != null ? productService.existBySku(value) : true;
	}

}
