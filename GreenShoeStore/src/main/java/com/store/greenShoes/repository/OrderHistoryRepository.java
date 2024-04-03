package com.store.greenShoes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store.greenShoes.model.OrderHistory;

public interface OrderHistoryRepository extends JpaRepository<OrderHistory, Long>{
	//@Query(value="select * from OrderHistory join cart_item_orderdata on orderdata.order_id=cart_item_orderdata.orderdata_id join cart_items on cart_items.id=cart_item_orderdata.cart_item_id join user as ct on ct.id=cart_items.id where ct.id=?1",nativeQuery=true)
	//List<OrderHistory> findByCustomer(Customer customer);

}
