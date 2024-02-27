package com.store.greenShoes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store.greenShoes.model.Credentials;

public interface CredentialRepository extends JpaRepository<Credentials, Long>{

}
