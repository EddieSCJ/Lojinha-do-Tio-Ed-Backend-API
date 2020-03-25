package com.codereddie.lojinha.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codereddie.lojinha.domain.Address;
import com.codereddie.lojinha.domain.City;
import com.codereddie.lojinha.domain.Client;
import com.codereddie.lojinha.domain.enums.ClientType;
import com.codereddie.lojinha.dto.ClientDTO;
import com.codereddie.lojinha.dto.ClientPOSTDTO;
import com.codereddie.lojinha.repository.AddressRepository;
import com.codereddie.lojinha.repository.ClientRepository;
import com.codereddie.lojinha.services.exceptions.DataIntegrityException;
import com.codereddie.lojinha.services.exceptions.ObjectNotFoundException;

@Service
public class ClientService {

	@Autowired
	private BCryptPasswordEncoder encrypt;
	
	@Autowired
	AddressRepository addressRepository;

	@Autowired
	private ClientRepository clientRepository;

	public Client findByID(Integer id) {
		Optional<Client> client = clientRepository.findById(id);

		return client.orElseThrow(
				() -> new ObjectNotFoundException("Object Not Found, ID: " + id + ", Type: " + Client.class.getName()));
	}

	public List<Client> findAll() {
		List<Client> client = clientRepository.findAll();

		return client;
	}

	@Transactional
	public Client insert(Client client) {
		client.setId(null);
		clientRepository.save(client);
		addressRepository.saveAll(client.getAddresses());
		return client;
	}

	public Client update(Client client) {
		Client newClient = findByID(client.getId());
		updateData(client, newClient);

		return clientRepository.save(newClient);
	}

	public void deleteById(Integer id) {
		findByID(id);
		try {
			clientRepository.deleteById(id);
		} catch (DataIntegrityViolationException dtve) {
			throw new DataIntegrityException("Client has orders and is not possible delete clients with orders");
		}
	}

	public Page<Client> findPage(Integer index, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(index, linesPerPage, Direction.valueOf(direction), orderBy);
		return clientRepository.findAll(pageRequest);

	}

	/**
	 * ========================================================= Auxiliar methods
	 * =============================================
	 */

	public Client fromDTO(ClientDTO clientDTO) {
		return new Client(clientDTO.getId(), clientDTO.getName(), clientDTO.getEmail(), null, null, null);
	}

	public Client fromDTO(ClientPOSTDTO clientPOSTDTO) {
		
		Client client = new Client(null, clientPOSTDTO.getName(), clientPOSTDTO.getEmail(),
				clientPOSTDTO.getCpfOuCnpj(), ClientType.toEnum(clientPOSTDTO.getClientType()), encrypt.encode(clientPOSTDTO.getPassword()) );

		City city = new City(clientPOSTDTO.getCityID(), null, null);

		Address address = new Address(null, clientPOSTDTO.getPlace(), clientPOSTDTO.getNumber(),
				clientPOSTDTO.getComplement(), clientPOSTDTO.getNeighborhood(), clientPOSTDTO.getCEP(), client, city);
		client.addAddress(address);

		client.addPhone(clientPOSTDTO.getPhone());
		
		if (clientPOSTDTO.getPhoneux() != null) {
			client.addPhone(clientPOSTDTO.getPhoneux());
		}

		return client;
	}

	private void updateData(Client client, Client newClient) {
		if (client.getName() != null) {
			newClient.setName(client.getName());
		}
		if (client.getEmail() != null) {
			newClient.setEmail(client.getEmail());
		}
	}

}
