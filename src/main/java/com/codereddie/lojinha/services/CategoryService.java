package com.codereddie.lojinha.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.codereddie.lojinha.domain.Category;
import com.codereddie.lojinha.repository.CategoryRepository;
import com.codereddie.lojinha.services.exceptions.DataIntegrityException;
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
	
	public Category update(Category category) {
		findByID(category.getId());
		return categoryRepository.save(category);
	}
	
	public void deleteById(Integer id) {
		findByID(id);
		try {
			categoryRepository.deleteById(id);
		} catch (DataIntegrityViolationException dtve) {
			throw new DataIntegrityException("Category has products and is not possible delete categories with products");
		}
	}
}
