package com.camilo.curso.springboot.app.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import com.camilo.curso.springboot.app.ProductValidation;
import com.camilo.curso.springboot.app.entities.Product;
import com.camilo.curso.springboot.app.services.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@Autowired
//	private ProductValidation productValidation;
	
	@GetMapping
	public List<Product> list() {
		return productService.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> view(@PathVariable Integer id) {
		Optional<Product> optionalProduct = productService.findById(id);
		if (optionalProduct.isPresent()) {
			return ResponseEntity.ok(optionalProduct.orElseThrow());
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<?> create(@Valid @RequestBody Product product, BindingResult result) {
//		productValidation.validate(product, result);
		if (result.hasFieldErrors()) {
			return validation(result);
		}
		Product productNew = productService.save(product);
		return ResponseEntity.status(HttpStatus.CREATED).body(productNew);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Product product, BindingResult result, @PathVariable Integer id) {
//		productValidation.validate(product, result);
		if (result.hasFieldErrors()) {
			return validation(result);
		}
		Optional<Product> optionalProduct = productService.update(id, product);
		if (optionalProduct.isPresent()) {
			return ResponseEntity.status(HttpStatus.CREATED).body(optionalProduct.orElseThrow());	
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		Optional<Product> optionalProduct = productService.delete(id);
		if (optionalProduct.isPresent()) {
			return ResponseEntity.ok(optionalProduct.orElseThrow());
		}
		return ResponseEntity.notFound().build();
	}
	
	private ResponseEntity<?> validation(BindingResult result) {
		Map<String, String> errors = new HashMap<>();
		result.getFieldErrors().forEach(
			error -> errors.put(
				error.getField(), 
				"El campo " + error.getField() + " " + error.getDefaultMessage()
			)
		);
		return ResponseEntity.badRequest().body(errors);
	}
}
