package com.store.greenShoes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store.greenShoes.model.ShippingAddress;

public interface ShippingAddressRepository extends JpaRepository<ShippingAddress, Long>{

}
