package com.codereddie.lojinha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.qos.logback.core.net.server.Client;

public interface AddressRepository extends JpaRepository<Client, Integer>{

}
