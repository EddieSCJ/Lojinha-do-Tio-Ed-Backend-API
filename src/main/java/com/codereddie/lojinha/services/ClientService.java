package com.codereddie.lojinha.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codereddie.lojinha.domain.Client;
import com.codereddie.lojinha.repository.ClientRepository;
import com.codereddie.lojinha.services.exceptions.ObjectNotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;
	
	public Client findByID(Integer id) {
		Optional<Client> client = clientRepository.findById(id);
		
		return client.orElseThrow(() ->
			new ObjectNotFoundException("Object Not Found, ID: " + id +
										", Type: " + Client.class.getName()));
	}
	
}
