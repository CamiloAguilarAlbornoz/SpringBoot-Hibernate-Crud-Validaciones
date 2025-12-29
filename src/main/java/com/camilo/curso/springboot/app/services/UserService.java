package com.camilo.curso.springboot.app.services;

import java.util.List;

import com.camilo.curso.springboot.app.entities.User;

public interface UserService {

	public List<User> findAll();
	public User save(User user);
	public boolean existsByUserName(String userName);
}
