package com.store.greenShoes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store.greenShoes.model.BillingAddress;

public interface BillingAddressRepository extends JpaRepository<BillingAddress, Long>{

}
