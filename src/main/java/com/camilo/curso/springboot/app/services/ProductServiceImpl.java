package com.camilo.curso.springboot.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.camilo.curso.springboot.app.entities.Product;
import com.camilo.curso.springboot.app.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Product> findAll() {
		return (List<Product>) productRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Product> findById(Integer id) {
		return productRepository.findById(id);
	}

	@Transactional
	@Override
	public Product save(Product product) {
		return productRepository.save(product);
	}

	@Override
	@Transactional
	public Optional<Product> update(Integer id, Product product) {
		Optional<Product> optionalProductDB = productRepository.findById(id);
		if (optionalProductDB.isPresent()) {
			Product productDB = optionalProductDB.orElseThrow();
			productDB.setProductName(product.getProductName());
			productDB.setPrice(product.getPrice());
			productDB.setDescription(product.getDescription());
			productDB.setSku(product.getSku());
			return Optional.of(productRepository.save(productDB));
		}
		return optionalProductDB;
	}

	@Transactional
	@Override
	public Optional<Product> delete(Integer id) {
		Optional<Product> optionalProductDB = productRepository.findById(id);
		optionalProductDB.ifPresent(productDB -> productRepository.delete(productDB));
		return optionalProductDB;
	}

	@Override
	@Transactional(readOnly = true)
	public boolean existBySku(String sku) {
		return productRepository.existsBySku(sku);
	}
}
