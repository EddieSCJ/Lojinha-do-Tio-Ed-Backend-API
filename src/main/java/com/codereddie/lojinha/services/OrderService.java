package com.codereddie.lojinha.services;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codereddie.lojinha.domain.Category;
import com.codereddie.lojinha.domain.Client;
import com.codereddie.lojinha.domain.OrderItem;
import com.codereddie.lojinha.domain.Orderr;
import com.codereddie.lojinha.domain.Payament;
import com.codereddie.lojinha.domain.PayamentWithBankBill;
import com.codereddie.lojinha.domain.enums.PayamentState;
import com.codereddie.lojinha.repository.OrderItemRepository;
import com.codereddie.lojinha.repository.OrderrRepository;
import com.codereddie.lojinha.repository.PayamentRepository;
import com.codereddie.lojinha.security.UserSS;
import com.codereddie.lojinha.services.exceptions.AuthorizationException;
import com.codereddie.lojinha.services.exceptions.ObjectNotFoundException;

@Service
public class OrderService {

	@Autowired
	private OrderrRepository orderRepository;
	
	@Autowired
	private ProductService  productService;
	
	@Autowired
	private AddressService  addressService;
	
	@Autowired
	private PayamentRepository payamentRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private EmailService emailService;
	
	public Orderr findByID(Integer id) {
		Optional<Orderr> order = orderRepository.findById(id);
		
		return order.orElseThrow(() ->
			new ObjectNotFoundException("Object Not Found, ID: " + id +
										", Type: " + Orderr.class.getName()));
	}
	
	@Transactional
	public Orderr insert(Orderr order) {
		order.setId(null);
		order.setInstantDate(new Date());
		order.setClient(clientService.findByID(order.getClient().getId()));
		order.getPayament().setPayamentState(PayamentState.PENDENTE);
		order.getPayament().setOrder(order);
		
		PayamentWithBankBill payament;
		
		if(order.getPayament() instanceof PayamentWithBankBill) {
			payament = (PayamentWithBankBill) order.getPayament();
			Calendar cal = Calendar.getInstance();
			cal.setTime(order.getInstantDate());
			cal.add(Calendar.DAY_OF_MONTH, 7);
			  
			payament.setExpirationDate(cal.getTime());
			
			payament.setOrder(order);
			order.setPayament(payament);
		}
		
		order = orderRepository.save(order);
		Payament pay = payamentRepository.save(order.getPayament());
		
		for (OrderItem oi : order.getItens()) {
			oi.setDiscount(0.0);
			oi.setProduct(productService.findByID(oi.getProduct().getId()));
			oi.setPrice(oi.getProduct().getPrice());
			oi.setOrder(order);
		}
		
		order.setAddress(addressService.findByID(order.getAddress().getId()));
		order.getClient().setEmail("levirlemos1230@gmail.com");
		orderItemRepository.saveAll(order.getItens());
		
		emailService.sendOrderConfirmationHTMLEmail(order);
		return order;
	}
	
	public Page<Orderr> findPage(Integer index, Integer linesPerPage, String orderBy, String direction){
		UserSS user = UserService.authenticated();
		
		PageRequest pageRequest =  PageRequest.of(index, linesPerPage, Direction.valueOf(direction), 
				orderBy);

		if(user == null) {
			throw new AuthorizationException("Acesso negado");
		}
		
		Client client = clientService.findByID(user.getId());
		return orderRepository.findByClient(client, pageRequest);
	}
	
}
