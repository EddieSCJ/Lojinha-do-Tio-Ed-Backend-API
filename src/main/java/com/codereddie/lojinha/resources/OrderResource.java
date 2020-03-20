package com.codereddie.lojinha.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codereddie.lojinha.domain.Orderr;
import com.codereddie.lojinha.services.OrderService;

@RestController
@RequestMapping(value="/orders")
public class OrderResource {
 
	@Autowired
	private OrderService orderService;
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> findById(@PathVariable Integer id) {
		
		Orderr order = orderService.findByID(id);
		return ResponseEntity.ok().body(order);
		
	}
	
}
