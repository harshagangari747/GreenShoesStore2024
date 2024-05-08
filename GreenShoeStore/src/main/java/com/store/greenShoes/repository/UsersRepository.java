package com.store.greenShoes.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.store.greenShoes.model.Users;

public interface UsersRepository extends JpaRepository<Users, Long>{
	@Query("select u from Users u where u.userName=?1")
	Users getByUserName(String username);
	@Query("select u from Users u where u.email=?1")
	Users getByEmail(String email);

	Users getReferenceByUserId(Long userId);
	
	@Query("select u from Users u where u.email=?1")
	Optional<Users> findByEmail(String email);
}
