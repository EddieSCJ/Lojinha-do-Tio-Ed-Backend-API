package com.codereddie.lojinha;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.codereddie.lojinha.domain.Category;
import com.codereddie.lojinha.domain.City;
import com.codereddie.lojinha.domain.Product;
import com.codereddie.lojinha.domain.State;
import com.codereddie.lojinha.repository.CategoryRepository;
import com.codereddie.lojinha.repository.CityRepository;
import com.codereddie.lojinha.repository.ProductRepository;
import com.codereddie.lojinha.repository.StateRepository;

@SpringBootApplication
public class LojinhaApplication implements CommandLineRunner{

	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	ProductRepository productRepository;

	@Autowired
	StateRepository stateRepository;
	
	@Autowired
	CityRepository cityRepository;
	
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
		
		State est1 = new State(null, "Minas");
		State est2 = new State(null, "Sampa");
		
		City city = new City(null, "Cidade1", est1);
		City city2 = new City(null, "Cidade2", est1);
		
		City city3 = new City(null, "Cidade3", est2);
		City city4 = new City(null, "Cidade4", est2);
		
		stateRepository.saveAll(Arrays.asList(est1, est2));
		cityRepository.saveAll(Arrays.asList(city, city2, city3, city4));
		
		informatica.addProduct(produto1);
		informatica.addProduct(produto2);
		produto1.addCategory(informatica);
		produto1.addCategory(escritorio);

		escritorio.addProduct(produto2);
		escritorio.addProduct(produto1);
		produto2.addCategory(escritorio);
		produto2.addCategory(informatica);
		
		// ! Every calls the mapped first, not the creator of join table
		categoryRepository.saveAll(Arrays.asList(informatica, escritorio));
		productRepository.saveAll(Arrays.asList(produto1, produto2));
	}

}
