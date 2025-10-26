package com.camilo.curso.springboot.app.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.camilo.curso.springboot.app.entities.Role;
import com.camilo.curso.springboot.app.entities.User;
import com.camilo.curso.springboot.app.repositories.RoleRepository;
import com.camilo.curso.springboot.app.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	@Transactional(readOnly = true)
	public List<User> findAll() {
		return (List<User>) userRepository.findAll();
	}

	@Override
	@Transactional
	public User save(User user) {
		Optional<Role> optionalRole = roleRepository.findByName("ROLE_USER");
		List<Role> rolesList = new ArrayList<>();
		optionalRole.ifPresent(role -> rolesList.add(role));
		if (user.isAdmin()) {
			Optional<Role> optionalRoleAdmin = roleRepository.findByName("ROLE_ADMIN");
			optionalRoleAdmin.ifPresent(role -> rolesList.add(role));
		}
		user.setRolesList(rolesList);
		user.setPassword(encoderPassword(user.getPassword()));
		return userRepository.save(user);
	}

	private String encoderPassword(String password) {
		return passwordEncoder.encode(password);
	}

}
