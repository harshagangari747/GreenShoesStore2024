package com.store.greenShoes.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.store.greenShoes.model.OnSaleProducts;
import com.store.greenShoes.model.Product;

public interface OnSaleProductRepository extends JpaRepository<OnSaleProducts, Long> {
	@Query("select o from OnSaleProducts o where o.productId=?1")
	OnSaleProducts findByProductId(Product id);

}
