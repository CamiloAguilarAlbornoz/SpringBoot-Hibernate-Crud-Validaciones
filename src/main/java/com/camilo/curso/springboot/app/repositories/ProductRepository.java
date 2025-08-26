package com.camilo.curso.springboot.app.repositories;

import org.springframework.data.repository.CrudRepository;

import com.camilo.curso.springboot.app.entities.Product;

public interface ProductRepository extends CrudRepository<Product, Integer> {

	public boolean existsBySku(String sku);
}
