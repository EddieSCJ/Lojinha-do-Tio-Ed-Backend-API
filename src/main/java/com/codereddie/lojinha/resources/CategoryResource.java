package com.codereddie.lojinha.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.codereddie.lojinha.domain.Category;
import com.codereddie.lojinha.dto.CategoryDTO;
import com.codereddie.lojinha.services.CategoryService;

@RestController
@RequestMapping(value="/categories")
public class CategoryResource {
 
	@Autowired
	private CategoryService categoryService;
	
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> findAll() {
		
		List<Category> categories = categoryService.findAll();
		List<CategoryDTO> dtoList = categories.stream().map(category -> new CategoryDTO(category)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(dtoList);
		
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> findById(@PathVariable Integer id) {
		
		Category category = categoryService.findByID(id);
		return ResponseEntity.ok().body(category);
		
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Category category){
		
		category = categoryService.insert(category);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(category.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
		
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@PathVariable Integer id, @RequestBody Category category){
		category.setId(id);
		category = categoryService.update(category);
		return ResponseEntity.noContent().build();
		
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
		
		categoryService.deleteById(id);
		return ResponseEntity.noContent().build();
		
	}
	
}
