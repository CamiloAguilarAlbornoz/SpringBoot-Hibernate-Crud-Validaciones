package com.camilo.curso.springboot.app.repositories;

import org.springframework.data.repository.CrudRepository;

import com.camilo.curso.springboot.app.entities.User;

public interface UserRepository extends CrudRepository<User, Integer> {

}
