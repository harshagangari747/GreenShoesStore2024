package com.store.greenShoes.repository;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.store.greenShoes.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

	List<Product> findByCategory(String category,  PageRequest pageable);

	@Query("SELECT p FROM Product p WHERE p.name LIKE %?1%")
	List<Product> searchProduct(String keyword);

}