package com.store.greenShoes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store.greenShoes.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long>{

}
