package com.codereddie.lojinha.repository;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codereddie.lojinha.domain.Client;
import com.codereddie.lojinha.domain.Orderr;

@Repository
public interface OrderrRepository extends JpaRepository<Orderr, Integer> {

	@Transactional
	Page<Orderr> findByClient(Client client, Pageable pageRequest);
	
}
