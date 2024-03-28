package com.store.greenShoes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.store.greenShoes.model.Size;

public interface SizeRepository extends JpaRepository<Size, Long>{
	@Query(value="select * from size s where s.product_id=?1",nativeQuery = true)
	List<Size> getByProductId(Long id);

}
