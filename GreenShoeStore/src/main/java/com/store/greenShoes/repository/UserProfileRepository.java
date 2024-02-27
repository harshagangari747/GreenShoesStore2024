package com.store.greenShoes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store.greenShoes.model.UserProfile;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long>{

}
