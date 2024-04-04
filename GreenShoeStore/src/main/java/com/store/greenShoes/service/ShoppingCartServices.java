package com.store.greenShoes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.greenShoes.DTO.CartItemDTO;
import com.store.greenShoes.model.CartItem;

import com.store.greenShoes.model.Product;
import com.store.greenShoes.model.Users;
import com.store.greenShoes.repository.CartItemRepository;
import com.store.greenShoes.repository.ProductRepository;

@Service
public class ShoppingCartServices {
	
	@Autowired
	public CartItemRepository cartItemRepository;
	
	@Autowired
	public ProductRepository productRepository;
	
	
	public List<CartItem> listCartItems(Users customer){
		return cartItemRepository.findByUser(customer);
	}
	public CartItemDTO putQuantity(CartItemDTO cartItemDTO) {//edit
		CartItem cartItem = cartItemRepository.getReferenceById(cartItemDTO.getCartId());
		cartItem.setQuantity(cartItemDTO.getQuantity());
		CartItem cartItem2= cartItemRepository.save(cartItem);
		return cartItemDTO;
		
		
	}

	public void removeProduct(Long cartId) {
		cartItemRepository.deleteById(cartId);
	}

	public CartItem addProduct(CartItem cartItem) {
		
		return cartItemRepository.save(cartItem);
	}
//	public float cartSum(CartItem c, Long userId) {
//		// TODO Auto-generated method stub
//		float subTotal=c.getSubTotal();
//		return subTotal;
//	}
//	
//	public void deleteAllCartItems(Long uid) {
//		cartItemRepository.deleteAll(uid);
//	}

}
