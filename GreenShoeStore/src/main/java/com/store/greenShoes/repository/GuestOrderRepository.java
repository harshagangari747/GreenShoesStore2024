package com.store.greenShoes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store.greenShoes.model.GuestOrders;

public interface GuestOrderRepository extends JpaRepository<GuestOrders, Long>{

}
