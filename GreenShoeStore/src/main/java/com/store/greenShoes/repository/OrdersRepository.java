package com.store.greenShoes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store.greenShoes.model.Orders;
import com.store.greenShoes.model.Users;

public interface OrdersRepository extends JpaRepository<Orders, Long>{

	List<Orders> findByUser(Users referenceById);

}
