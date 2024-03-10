package com.store.greenShoes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store.greenShoes.model.OrderHistory;

public interface OrderHistoryRepository extends JpaRepository<OrderHistory, Long>{

}
