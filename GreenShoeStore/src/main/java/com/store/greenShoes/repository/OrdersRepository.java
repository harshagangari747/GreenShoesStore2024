package com.store.greenShoes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.store.greenShoes.model.Orders;
import com.store.greenShoes.model.Users;

public interface OrdersRepository extends JpaRepository<Orders, Long>{

	List<Orders> findByUser(Users referenceById);
	@Query(value="select o from Orders o where o.rderID=?1 and o.user=?2",nativeQuery = true)
	Orders getByUserAndOrder(Long oid, Users user);

}
