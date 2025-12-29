package com.camilo.curso.springboot.app.repositories;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.camilo.curso.springboot.app.entities.User;

public interface UserRepository extends CrudRepository<User, Integer> {

	public boolean existsByUserName(String userName);
	public Optional<User> findByUserName(String userName);
}
