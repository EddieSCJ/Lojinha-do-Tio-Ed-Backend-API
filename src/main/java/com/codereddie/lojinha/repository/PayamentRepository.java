package com.codereddie.lojinha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codereddie.lojinha.domain.Payament;

@Repository
public interface PayamentRepository extends JpaRepository<Payament, Integer> {

}
