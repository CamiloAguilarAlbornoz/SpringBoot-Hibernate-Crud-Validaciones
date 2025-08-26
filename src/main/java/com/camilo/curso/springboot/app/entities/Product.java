package com.camilo.curso.springboot.app.entities;

import com.camilo.curso.springboot.app.validation.IsExistDB;
import com.camilo.curso.springboot.app.validation.Require;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
//import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "products")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idProduct;
	
	@NotEmpty(message = "{NotEmpty.product.name}")
	@Size(min = 3, max = 20)
	private String productName;
	
	@Min(50000)
	@NotNull(message = "{NotNull.product.price}")
	private Integer price;
	
	@Require
	private String description;
	
	@IsExistDB
	private String sku;

	public Product() {
	}
	
	public Product(String productName, Integer price, String description, String sku) {
		this.productName = productName;
		this.price = price;
		this.description = description;
		this.sku = sku;
	}

	public Integer getIdProduct() {
		return idProduct;
	}
	
	public void setIdProduct(Integer idProduct) {
		this.idProduct = idProduct;
	}
	
	public String getProductName() {
		return productName;
	}
	
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public Integer getPrice() {
		return price;
	}
	
	public void setPrice(Integer price) {
		this.price = price;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}
}
