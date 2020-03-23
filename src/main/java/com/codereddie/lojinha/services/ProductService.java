package com.codereddie.lojinha.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.codereddie.lojinha.domain.Category;
import com.codereddie.lojinha.domain.Product;
import com.codereddie.lojinha.repository.CategoryRepository;
import com.codereddie.lojinha.repository.ProductRepository;
import com.codereddie.lojinha.services.exceptions.ObjectNotFoundException;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryRepository categoryRepository;
	
	public Product findByID(Integer id) {
		Optional<Product> product = productRepository.findById(id);

		return product.orElseThrow(() -> new ObjectNotFoundException(
				"Object Not Found, ID: " + id + ", Type: " + Product.class.getName()));
	}

	public Page<Product> search(String name, List<Integer> ids, Integer index, Integer linesPerPage, String orderBy,
			String direction) {
		
		PageRequest pageRequest = PageRequest.of(index, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Category> categories = categoryRepository.findAllById(ids);
		
		return productRepository.search(name, categories, pageRequest);

	}
}
