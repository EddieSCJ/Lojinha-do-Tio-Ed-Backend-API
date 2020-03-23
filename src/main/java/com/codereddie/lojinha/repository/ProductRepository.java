package com.codereddie.lojinha.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.codereddie.lojinha.domain.Category;
import com.codereddie.lojinha.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	@Query("SELECT DISTINCT product FROM Product product INNER JOIN product.categories cat WHERE product.name LIKE %:name% AND cat in :categories")
	Page<Product> search(@Param("name") String name, @Param("categories") List<Category> categories,
			Pageable pageRequest);
	
//	Page<Product> findDistinctByNameContainingAndCategoriesIn(@Param("name") String name, @Param("categories") List<Category> categories,
//			PageRequest pageRequest);
}
