package com.codereddie.lojinha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codereddie.lojinha.domain.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
