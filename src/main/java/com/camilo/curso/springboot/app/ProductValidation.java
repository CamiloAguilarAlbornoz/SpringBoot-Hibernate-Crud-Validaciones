package com.camilo.curso.springboot.app;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.camilo.curso.springboot.app.entities.Product;

@Component
public class ProductValidation implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Product.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Product product = (Product) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "productName", null, "Es requerido!");
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "{NotBlank.product.description}");
		if (product.getDescription() == null || product.getDescription().isBlank()) {
			errors.rejectValue("description", null, "Es requerido, por favor");
		}
		if (product.getPrice() == null) {
			errors.rejectValue("price", null, "No puede ser nulo");
		} else if (product.getPrice() < 500) {
			errors.rejectValue("price", "Debe ser un valor numerico mayor o igual a 50000");	
		}
	}
}
