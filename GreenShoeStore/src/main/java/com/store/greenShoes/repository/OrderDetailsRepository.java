package com.store.greenShoes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store.greenShoes.model.OrderDetails;
import com.store.greenShoes.model.Orders;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long>{

	List<OrderDetails> findByOrder(Orders order);

}
