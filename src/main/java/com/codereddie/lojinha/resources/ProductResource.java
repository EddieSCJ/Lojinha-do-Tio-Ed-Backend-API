package com.codereddie.lojinha.resources;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codereddie.lojinha.domain.Product;
import com.codereddie.lojinha.dto.ProductDTO;
import com.codereddie.lojinha.services.ProductService;

@RestController
@RequestMapping(value = "/products")
public class ProductResource {

	@Autowired
	private ProductService productService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> findById(@PathVariable Integer id) {

		Product product = productService.findByID(id);
		return ResponseEntity.ok().body(product);

	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<ProductDTO>> findPage(
			@RequestParam(name = "name", defaultValue = "") String name,
			@RequestParam(name = "categories", defaultValue = "") String categories,
			@RequestParam(name = "page", defaultValue = "0") Integer index,
			@RequestParam(name = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(name = "orderBy", defaultValue = "name") String orderBy,
			@RequestParam(name = "direction", defaultValue = "ASC") String direction) throws UnsupportedEncodingException {

		List<Integer> catIdList = Arrays.asList(categories.split(",")).stream().map(categoryId -> Integer.parseInt(categoryId.trim()))
				.collect(Collectors.toList());
		
		name = URLDecoder.decode(name, "UTF-8");
		
		Page<Product> products = productService.search(name, catIdList, index, linesPerPage, orderBy, direction);
		Page<ProductDTO> dtoList = products.map(product -> new ProductDTO(product));

		return ResponseEntity.ok().body(dtoList);

	}

}
