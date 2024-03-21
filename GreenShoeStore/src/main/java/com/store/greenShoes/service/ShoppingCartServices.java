package com.store.greenShoes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.greenShoes.model.CartItem;
import com.store.greenShoes.model.Customer;
import com.store.greenShoes.model.Product;
import com.store.greenShoes.repository.CartItemRepository;
import com.store.greenShoes.repository.CustomerRepository;
import com.store.greenShoes.repository.ProductRepository;

@Service
public class ShoppingCartServices {
	
	@Autowired
	public CartItemRepository cartItemRepository;
	
	@Autowired
	public ProductRepository productRepository;
	
	@Autowired
	public CustomerRepository userRepository;
	
	public List<CartItem> listCartItems(Customer customer){
		return cartItemRepository.findByUser(customer);
	}
	public CartItem putQuantity(Long productId, Long quantity, Customer customer) {//edit
		long addedQuantity=quantity;
		
		Product product=productRepository.getReferenceById(productId);
		
		CartItem cartItem=cartItemRepository.findByUserAndProduct(customer, product);
		
		cartItem.setQuantity(addedQuantity);
		cartItem.setSubTotal(quantity*product.getPrice());
		CartItem c=cartItemRepository.save(cartItem);
		
		return c;
		
	}

	public long updateQuantity(Long productId, Long quantity, Long customerId) {
		cartItemRepository.updateQuantity(quantity,productId,customerId);
		Product product=productRepository.getReferenceById(productId);
		long subTotal=product.getPrice()*quantity;
		return subTotal;
	}

	public void removeProduct(Long cartId) {
		cartItemRepository.deleteById(cartId);
	}

	//Post 
	public CartItem addProduct(CartItem cartItem) {
		return cartItemRepository.save(cartItem);
	}
	public Long cartSum(CartItem c, Long userId) {
		// TODO Auto-generated method stub
		Long subTotal=c.getSubTotal();
		return subTotal;
	}
	
	public void deleteAllCartItems(Long uid) {
		cartItemRepository.deleteAll(uid);
	}

}
