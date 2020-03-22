package com.codereddie.lojinha.services;

import java.util.List;
import java.util.Optional;

import javax.el.MethodNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.codereddie.lojinha.domain.Client;
import com.codereddie.lojinha.domain.Client;
import com.codereddie.lojinha.dto.ClientDTO;
import com.codereddie.lojinha.repository.ClientRepository;
import com.codereddie.lojinha.repository.ClientRepository;
import com.codereddie.lojinha.services.exceptions.DataIntegrityException;
import com.codereddie.lojinha.services.exceptions.ObjectNotFoundException;

@Service
public class ClientService {

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

	public Client insert(Client client) {
		client.setId(null);
		return clientRepository.save(client);
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
		return new Client(clientDTO.getId(), clientDTO.getName(), clientDTO.getEmail(), null, null);
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
