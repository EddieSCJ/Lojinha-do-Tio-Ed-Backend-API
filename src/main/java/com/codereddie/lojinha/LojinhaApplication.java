package com.codereddie.lojinha;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.codereddie.lojinha.domain.Category;
import com.codereddie.lojinha.domain.Product;
import com.codereddie.lojinha.repository.CategoryRepository;
import com.codereddie.lojinha.repository.ProductRepository;

@SpringBootApplication
public class LojinhaApplication implements CommandLineRunner{

	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(LojinhaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Category informatica = new Category(null,"Informática");
		Category escritorio = new Category(null,"Escritório");
		
		Product produto1 = new Product(null, "P1", 1.0);
		Product produto2 = new Product(null, "P2", 2.0);
		
		informatica.addProducts(produto1);
		informatica.addProducts(produto2);
		produto1.addCategories(informatica);
		produto1.addCategories(escritorio);

		escritorio.addProducts(produto2);
		escritorio.addProducts(produto1);
		produto2.addCategories(escritorio);
		produto2.addCategories(informatica);
		
		// ! Every calls the mapped first, not the creator of join table
		categoryRepository.saveAll(Arrays.asList(informatica, escritorio));
		productRepository.saveAll(Arrays.asList(produto1, produto2));
	}

}
