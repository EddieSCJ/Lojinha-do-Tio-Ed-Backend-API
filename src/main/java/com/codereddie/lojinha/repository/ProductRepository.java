package com.codereddie.lojinha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codereddie.lojinha.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
