package com.store.greenShoes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.store.greenShoes.model.Color;
import com.store.greenShoes.model.Product;
import com.store.greenShoes.model.ProductSizeColor;
import com.store.greenShoes.model.Size;

public interface ProductSizeColorRepository extends JpaRepository<ProductSizeColor, Long> {

	//List<ProductSizeColor> getByProductId(Long id);
	
	@Query("select psc from ProductSizeColor psc where psc.productId=?1 and psc.sizeId=?2")
	List<ProductSizeColor> findByProductSize(Product product, Size size);
	
	@Query("select psc from ProductSizeColor psc where psc.productId=?1")
	List<ProductSizeColor> findByProduct(Product product);
	@Query("select psc from ProductSizeColor psc where psc.productId=?1 and psc.sizeId=?2 and psc.colorId=?3" )
	ProductSizeColor findByProductSizeColor(Product product, Size size, Color color);

	//List<ProductSizeColor> findByProductSize(Long id, Long id2);
	

}
