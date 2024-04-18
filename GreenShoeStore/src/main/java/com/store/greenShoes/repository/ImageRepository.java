package com.store.greenShoes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.store.greenShoes.model.Image;
import com.store.greenShoes.model.Product;


public interface ImageRepository extends JpaRepository<Image, Long>{
	@Query(value="select * from image s where s.product_id=?1",nativeQuery = true)
	List<Image> getByProductId(Long id);

	List<Image> findByProductId(Product product);
}
