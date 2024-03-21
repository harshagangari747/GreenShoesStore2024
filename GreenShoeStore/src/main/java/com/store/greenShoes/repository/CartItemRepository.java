package com.store.greenShoes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.store.greenShoes.model.CartItem;
import com.store.greenShoes.model.Customer;
import com.store.greenShoes.model.Product;

import jakarta.transaction.Transactional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
public List<CartItem> findByUser(Customer customer);
	
	
	public CartItem findByUserAndProduct(Customer customer,Product product);

	@Query("update CartItem c set c.quantity = ?1 where c.product.id=?2 AND c.user.id=?3")
	@Modifying
	public void updateQuantity(Long quantity, Long productId, Long customerId);
	

	public void deleteById(Long cartId);


	@Modifying
	@Transactional
	@Query("delete from CartItem c where c.user.id=?1")
	public void deleteAll(Long uid);
	
}
