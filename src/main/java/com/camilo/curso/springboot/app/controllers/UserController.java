package com.camilo.curso.springboot.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.camilo.curso.springboot.app.entities.User;
import com.camilo.curso.springboot.app.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping
	public List <User> getAllUsers() {
		return userService.findAll();
	}
	
	@PostMapping("save")
	public ResponseEntity<?> saveUser(@RequestBody User user) {
		return ResponseEntity.status(HttpStatus.CREATED).body(
			userService.save(user)
		);
	}
}
