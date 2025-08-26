package com.camilo.curso.springboot.app.services;

import java.util.List;
import java.util.Optional;

import com.camilo.curso.springboot.app.entities.Product;

public interface ProductService {

	public List<Product> findAll();
	public Optional<Product> findById(Integer id);
	public Product save(Product product);
	public Optional<Product> update(Integer id, Product product);
	public Optional<Product> delete(Integer id);
	public boolean existBySku(String sku);
}
