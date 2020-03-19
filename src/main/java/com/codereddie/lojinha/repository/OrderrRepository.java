package com.codereddie.lojinha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codereddie.lojinha.domain.Orderr;

@Repository
public interface OrderrRepository extends JpaRepository<Orderr, Integer> {

}
