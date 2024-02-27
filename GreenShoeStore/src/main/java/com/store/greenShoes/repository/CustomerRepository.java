package com.store.greenShoes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store.greenShoes.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
