package com.codereddie.lojinha.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codereddie.lojinha.domain.Category;

@RestController
@RequestMapping(value="/categories")
public class CategoryResource {
 
	@RequestMapping(method = RequestMethod.GET)
	public List<Category> list() {
		
		Category category = new Category(1, "Informática");
		Category category2 = new Category(2, "Eletrodomésticos");
		
		List<Category> categories = new ArrayList<Category>();
		categories.add(category);
		categories.add(category2);
		
		return categories;
	}
	
}
