package com.codereddie.lojinha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.codereddie.lojinha.domain.Client;


public interface ClientRepository extends JpaRepository<Client, Integer>{

	@Transactional(readOnly = true)
	Client findByEmail(String email);
	
	@Transactional(readOnly = true)
	Client findByCpfOuCnpj(String cpfOuCnpj);
	
}
