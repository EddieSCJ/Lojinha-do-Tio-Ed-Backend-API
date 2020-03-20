package com.codereddie.lojinha.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codereddie.lojinha.domain.Orderr;
import com.codereddie.lojinha.repository.OrderrRepository;
import com.codereddie.lojinha.services.exceptions.ObjectNotFoundException;

@Service
public class OrderService {

	@Autowired
	private OrderrRepository orderRepository;
	
	public Orderr findByID(Integer id) {
		Optional<Orderr> order = orderRepository.findById(id);
		
		return order.orElseThrow(() ->
			new ObjectNotFoundException("Object Not Found, ID: " + id +
										", Type: " + Orderr.class.getName()));
	}
	
}
