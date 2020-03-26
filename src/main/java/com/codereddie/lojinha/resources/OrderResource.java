package com.codereddie.lojinha.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.codereddie.lojinha.domain.Orderr;
import com.codereddie.lojinha.services.OrderService;

@RestController
@RequestMapping(value = "/orders")
public class OrderResource {

	@Autowired
	private OrderService orderService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> findById(@PathVariable Integer id) {

		Orderr order = orderService.findByID(id);
		return ResponseEntity.ok().body(order);

	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert( @RequestBody Orderr order) {

		orderService.insert(order);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(order.getId()).toUri();

		return ResponseEntity.created(uri).build();

	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<Page> findPage(@RequestParam(name = "page", defaultValue = "0") Integer index,
			@RequestParam(name = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(name = "orderBy", defaultValue = "name") String orderBy,
			@RequestParam(name = "direction", defaultValue = "ASC") String direction) {

		Page<Orderr> orders = orderService.findPage(index, linesPerPage, orderBy, direction);

		return ResponseEntity.ok().body(orders);

	}
}
