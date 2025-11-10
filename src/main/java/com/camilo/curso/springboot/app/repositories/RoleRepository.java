package com.camilo.curso.springboot.app.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.camilo.curso.springboot.app.entities.Role;


public interface RoleRepository extends CrudRepository<Role, Integer> {

	public Optional<Role> findByNameRol(String nameRol);
}
