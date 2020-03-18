package com.codereddie.lojinha.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codereddie.lojinha.domain.Client;
import com.codereddie.lojinha.services.ClientService;

@RestController
@RequestMapping(value="/clients")
public class ClientResource {
 
	@Autowired
	private ClientService clientService;
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> findById(@PathVariable Integer id) {
		
		Client client = clientService.findByID(id);
		return ResponseEntity.ok().body(client);
		
	}
	
}
