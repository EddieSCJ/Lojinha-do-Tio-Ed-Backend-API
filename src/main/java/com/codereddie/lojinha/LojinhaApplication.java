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
import com.codereddie.lojinha.repository.OrderrRepository;
import com.codereddie.lojinha.repository.PayamentRepository;
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
	
	@Autowired
	OrderrRepository orderrRepository;
	
	@Autowired
	PayamentRepository payamentRepository;
	
	@Autowired
	ClientRepository clientRepository;
	
	@Autowired
	AddressRepository addressRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(LojinhaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		Category cat1 = new Category(null, "Informática");
		Category cat2 = new Category(null, "Escritório");
		
		Product p1 = new Product(null, "Computador", 2000.00);
		Product p2 = new Product(null, "Impressora", 800.00);
		Product p3 = new Product(null, "Mouse", 80.00);
		
		cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProducts().addAll(Arrays.asList(p2));
		
		p1.getCategories().addAll(Arrays.asList(cat1));
		p2.getCategories().addAll(Arrays.asList(cat1, cat2));
		p3.getCategories().addAll(Arrays.asList(cat1));
				
		categoryRepository.saveAll(Arrays.asList(cat1, cat2));
		productRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		State est1 = new State(null, "Minas Gerais");
		State est2 = new State(null, "São Paulo");
		
		City c1 = new City(null, "Uberlândia", est1);
		City c2 = new City(null, "São Paulo", est2);
		City c3 = new City(null, "Campinas", est2);
		
		est1.getCities().addAll(Arrays.asList(c1));
		est2.getCities().addAll(Arrays.asList(c2, c3));
		stateRepository.saveAll(Arrays.asList(est1, est2));
		cityRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Client cli1 = new Client(null, "Maria Silva", "maria@gmail.com", "36378912377", ClientType.PESSOAFISICA);
		
		cli1.getPhones().addAll(Arrays.asList("27363323", "93838393"));
		
		Address e1 = new Address(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", cli1, c1);
		Address e2 = new Address(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);
		
		cli1.getAddresses().addAll(Arrays.asList(e1, e2));

		clientRepository.saveAll(Arrays.asList(cli1));
		addressRepository.saveAll(Arrays.asList(e1, e2));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");

		Orderr ped1 = new Orderr(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Orderr ped2 = new Orderr(null, sdf.parse("10/10/2017 19:35"), cli1, e2);

		Payament pagto1 = new PayamentWithCreditCard(null, PayamentState.QUITADO, ped1, 6);
		ped1.setPayament(pagto1);

		Payament pagto2 = new PayamentWithBankBill(null, PayamentState.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPayament(pagto2);

		cli1.getOrders().addAll(Arrays.asList(ped1, ped2));

		orderrRepository.saveAll(Arrays.asList(ped1, ped2));
		payamentRepository.saveAll(Arrays.asList(pagto1, pagto2));
	
	}

}
