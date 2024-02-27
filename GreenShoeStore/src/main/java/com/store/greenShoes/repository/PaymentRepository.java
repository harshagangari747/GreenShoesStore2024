package com.store.greenShoes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store.greenShoes.model.PaymentInformation;

public interface PaymentRepository extends JpaRepository<PaymentInformation, Long>{

}
