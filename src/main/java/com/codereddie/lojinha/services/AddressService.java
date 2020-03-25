package com.codereddie.lojinha.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codereddie.lojinha.domain.Address;
import com.codereddie.lojinha.domain.Client;
import com.codereddie.lojinha.repository.AddressRepository;
import com.codereddie.lojinha.services.exceptions.ObjectNotFoundException;

@Service
public class AddressService {

	@Autowired
	AddressRepository addressRepository;

	public Address findByID(Integer id) {
		Optional<Address> address = addressRepository.findById(id);

		return address.orElseThrow(
				() -> new ObjectNotFoundException("Object Not Found, ID: " + id + ", Type: " + Client.class.getName()));
	}

}
