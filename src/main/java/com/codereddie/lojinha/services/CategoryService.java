package com.codereddie.lojinha.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codereddie.lojinha.domain.Category;
import com.codereddie.lojinha.repository.CategoryRepository;
import com.codereddie.lojinha.services.exceptions.ObjectNotFoundException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	public List<Category> findAll() {
		List<Category> category = categoryRepository.findAll();
		
		return category;
	}
	
	public Category findByID(Integer id) {
		Optional<Category> category = categoryRepository.findById(id);
		
		return category.orElseThrow(() ->
			new ObjectNotFoundException("Object Not Found, ID: " + id +
										", Type: " + Category.class.getName()));
	}
	
	public Category insert(Category category) {
		category.setId(null);
		return categoryRepository.save(category);
	}
}
