package com.codereddie.lojinha;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.codereddie.lojinha.domain.Address;
import com.codereddie.lojinha.domain.Category;
import com.codereddie.lojinha.domain.City;
import com.codereddie.lojinha.domain.Client;
import com.codereddie.lojinha.domain.OrderItem;
import com.codereddie.lojinha.domain.Orderr;
import com.codereddie.lojinha.domain.Payament;
import com.codereddie.lojinha.domain.PayamentWithBankBill;
import com.codereddie.lojinha.domain.PayamentWithCreditCard;
import com.codereddie.lojinha.domain.Product;
import com.codereddie.lojinha.domain.State;
import com.codereddie.lojinha.domain.enums.ClientType;
import com.codereddie.lojinha.domain.enums.PayamentState;
import com.codereddie.lojinha.repository.AddressRepository;
import com.codereddie.lojinha.repository.CategoryRepository;
import com.codereddie.lojinha.repository.CityRepository;
import com.codereddie.lojinha.repository.ClientRepository;
import com.codereddie.lojinha.repository.OrderItemRepository;
import com.codereddie.lojinha.repository.OrderrRepository;
import com.codereddie.lojinha.repository.PayamentRepository;
import com.codereddie.lojinha.repository.ProductRepository;
import com.codereddie.lojinha.repository.StateRepository;

@SpringBootApplication
public class LojinhaApplication implements CommandLineRunner{


	public static void main(String[] args) {
		SpringApplication.run(LojinhaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
	}
	

}
