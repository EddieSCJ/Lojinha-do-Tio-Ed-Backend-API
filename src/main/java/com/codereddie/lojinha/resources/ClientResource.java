package com.codereddie.lojinha.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.codereddie.lojinha.domain.Client;
import com.codereddie.lojinha.domain.Client;
import com.codereddie.lojinha.dto.ClientDTO;
import com.codereddie.lojinha.dto.ClientDTO;
import com.codereddie.lojinha.dto.ClientPOSTDTO;
import com.codereddie.lojinha.services.ClientService;

@RestController
@RequestMapping(value = "/clients")
public class ClientResource {

	@Autowired
	private ClientService clientService;

	/**
	 * Método retorna somente os clientes, sem dados adicionais e afins
	 * 
	 * @author Edcleidson Júnior
	 * @noParam
	 * @return Lista de objetos DTO - retorna a lista de objetos com os dados
	 *         requisitados
	 */
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> findAll() {

		List<Client> clients = clientService.findAll();
		List<ClientDTO> dtoList = clients.stream().map(client -> new ClientDTO(client)).collect(Collectors.toList());

		return ResponseEntity.ok().body(dtoList);

	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page> findPage(@RequestParam(name = "page", defaultValue = "0") Integer index,
			@RequestParam(name = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(name = "orderBy", defaultValue = "name") String orderBy,
			@RequestParam(name = "direction", defaultValue = "ASC") String direction) {

		Page<Client> clients = clientService.findPage(index, linesPerPage, orderBy, direction);
		Page<ClientDTO> dtoList = clients.map(client -> new ClientDTO(client));

		return ResponseEntity.ok().body(dtoList);

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> findById(@PathVariable Integer id) {

		Client client = clientService.findByID(id);
		return ResponseEntity.ok().body(client);

	}

	/**
	 * ==================================================== INICIO POST, PUT DELETE
	 * ================================================
	 **/

	/**
	 * Recebe e insere no banco um conjunto de dados do cliente via objeto DTO
	 * 
	 * @author Edcleidson Júnior
	 * @param Objeto DTO - Cliente
	 * @return URI - Endereço para acesso do objeto selecionado
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody ClientPOSTDTO clientPOSTDTO) {

		Client client = clientService.fromDTO(clientPOSTDTO);

		clientService.insert(client);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(client.getId()).toUri();

		return ResponseEntity.created(uri).build();

	}

	/**
	 * Recebe e atualiza no banco um conjunto de dados do cliente via objeto DTO
	 * 
	 * @author Edcleidson Júnior
	 * @param Objeto  DTO - Cliente
	 * @param Integer - ID
	 * @return ?
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@PathVariable Integer id, @Valid @RequestBody ClientDTO clientDTO) {
		clientDTO.setId(id);

		Client client = clientService.fromDTO(clientDTO);
		clientService.update(client);

		return ResponseEntity.noContent().build();
	}

	/**
	 * Deleta no banco um objeto pelo ID
	 * 
	 * @author Edcleidson Júnior
	 * @param Integer - ID
	 * @return ?
	 */
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteById(@PathVariable Integer id) {

		clientService.deleteById(id);
		return ResponseEntity.noContent().build();

	}

}
