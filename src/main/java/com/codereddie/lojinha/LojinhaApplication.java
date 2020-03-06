package com.codereddie.lojinha;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.codereddie.lojinha.domain.Category;
import com.codereddie.lojinha.repository.CategoryRepository;
import com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

@SpringBootApplication
public class LojinhaApplication implements CommandLineRunner{

	@Autowired
	CategoryRepository categoryRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(LojinhaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Category informatica = new Category(null,"Informática");
		Category escritorio = new Category(null,"Escritório");

		categoryRepository.saveAll(Arrays.asList(informatica, escritorio));
	}

}
