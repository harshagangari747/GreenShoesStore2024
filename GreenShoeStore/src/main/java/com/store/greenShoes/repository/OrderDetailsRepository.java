package com.store.greenShoes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.store.greenShoes.model.OrderDetails;
import com.store.greenShoes.model.Orders;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long>{

	List<OrderDetails> findByOrder(Orders order);
	@Modifying
	@Query("delete from OrderDetails o where o.order=?1")
	void deleteByOrder(Orders order);

}
