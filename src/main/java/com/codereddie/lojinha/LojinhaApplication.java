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
	
	}

}
