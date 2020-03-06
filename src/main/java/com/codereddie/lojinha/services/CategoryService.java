package com.codereddie.lojinha.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ws.server.endpoint.annotation.Endpoint;

import com.codereddie.lojinha.domain.Category;
import com.codereddie.lojinha.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	public Category findByID(Integer id) {
		Optional<Category> category = categoryRepository.findById(id);
		return category.orElse(null);
	}
	
}
