package com.camilo.curso.springboot.app.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.camilo.curso.springboot.app.entities.User;
import com.camilo.curso.springboot.app.repositories.UserRepository;

@Service
public class JpaUserDetailService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> optUser = userRepository.findByUserName(username);
		if(optUser.isEmpty()) {
			throw new UsernameNotFoundException(String.format("Username %s no existe en el sistema", username));
		}
		User user = optUser.orElseThrow();
		List<GrantedAuthority> authorities = user.getRolesList()
				.stream()
				.map(role -> new SimpleGrantedAuthority(role.getnameRol()))
				.collect(Collectors.toList());
		return new org.springframework.security.core.userdetails.User(
			user.getUserName(),
			user.getPassword(),
			user.isEnabled(),
			true,
			true,
			true,
			authorities
		);
	}

}
