package com.codereddie.lojinha.dto;

import java.io.Serializable;

import com.codereddie.lojinha.domain.Product;

public class ProductDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String name;
	private Double price;
	
	public ProductDTO() {
		
	}
	
	public ProductDTO(Product product) {
		super();
		this.id = product.getId();
		this.name = product.getName();
		this.price = product.getPrice();
	}


	public ProductDTO(Integer id, String name, Double price) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	
}
