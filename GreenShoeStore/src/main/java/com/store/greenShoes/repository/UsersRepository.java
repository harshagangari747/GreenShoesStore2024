package com.store.greenShoes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.store.greenShoes.model.UserProfile;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long>{
	@Query("select u from UserProfile u where u.userName=?1")
	UserProfile getByUserName(String username);
}
