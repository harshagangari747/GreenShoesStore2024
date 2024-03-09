package com.store.greenShoes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store.greenShoes.model.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Long>{

}
