package com.store.greenShoes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.store.greenShoes.model.CartItem;
import com.store.greenShoes.model.Color;
import com.store.greenShoes.model.Customer;
import com.store.greenShoes.model.Product;
import com.store.greenShoes.model.Size;
import com.store.greenShoes.repository.CartItemRepository;
import com.store.greenShoes.repository.ColorRepository;
import com.store.greenShoes.repository.CustomerRepository;
import com.store.greenShoes.repository.ProductRepository;
import com.store.greenShoes.repository.SizeRepository;
import com.store.greenShoes.service.ShoppingCartServices;

import jakarta.transaction.Transactional;

@RestController
//@CrossOrigin(origins="http://localhost:5923",methods = {RequestMethod.DELETE,RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT})
public class ShoppingCartController {
	
	@Autowired
	private ShoppingCartServices shoppingCartServices;
	
	@Autowired
	private CustomerRepository userRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CartItemRepository cartItemRepository;
	
	@Autowired
	private SizeRepository sizeRepository;
	
	@Autowired
	private ColorRepository colorRepository;

	
	@GetMapping("/cart/{uid}")
	public List<CartItem> showShoppingCart(@PathVariable("uid") Long customerId) {
		Customer customer=userRepository.getReferenceById(customerId);
		List<CartItem> cartItems=shoppingCartServices.listCartItems(customer);
		return cartItems;
	}
	
	@PostMapping("/cart/{uid}/{pid}")
	public CartItem addProduct(@RequestBody CartItem cartItem,@PathVariable("uid") Long customerId,@PathVariable("pid") Long productId) {
//		Product product=productRepository.getReferenceById(productId);
//		Customer customer=userRepository.getReferenceById(customerId);
//		cartItem.setQuantity(1L);
//		cartItem.setSubTotal(product.getPrice()*cartItem.getQuantity());
//		System.out.println(product.getPrice());
//		System.out.println(product.getQuantity());
//		cartItem.setUser(customer);
//		cartItem.setProduct(product);
		Product product=productRepository.getReferenceById(productId);
		Customer customer=userRepository.getReferenceById(customerId);
		Size size = sizeRepository.getReferenceById(cartItem.getSize().getID());
		Color color = colorRepository.getReferenceById(cartItem.getColor().getID());
		//cartItem.setQuantity(1L);
		cartItem.setSubTotal(product.getPrice()*cartItem.getQuantity());
		System.out.println(product.getPrice());
		System.out.println(product.getQuantity());
		cartItem.setUser(customer);
		cartItem.setProduct(product);
		cartItem.setSize(size);
		cartItem.setColor(color);
		return shoppingCartServices.addProduct(cartItem);
	}
	/*
	@PostMapping("/cart/post/{uid}/{pid}/{qty}")//edit this api as per S
	public Long addquantity(@PathVariable("pid") Long productId,
			@PathVariable("qty") Long quantity,@PathVariable("uid") Long customerId) {
		Customer customer=customerRepository.getById(customerId);
		Long addedQuantity=shoppingCartServices.addQuantity(productId, quantity, customer);
		return addedQuantity;
	}*/
	@PutMapping("/cart/put/{uid}/{pid}")//Post
	public CartItem addquantity(@PathVariable("pid") Long productId,
			@RequestBody CartItem cartItem,@PathVariable("uid") Long customerId) {
		Customer customer=userRepository.getReferenceById(customerId);
		return shoppingCartServices.putQuantity(productId, cartItem.getQuantity(), customer);
	}
	/*
	@PutMapping("/cart/update/{uid}/{pid}/{qty}")//put
	public Long updateQuantity(@PathVariable("pid") Long productId, 
			@PathVariable("qty") Long quantity,@PathVariable("uid") Long customerId) {
	
		Long subTotal=shoppingCartServices.updateQuantity(productId,quantity,customerId);
		return subTotal;
	}*/
	
	@Transactional
	@DeleteMapping("/cart/remove/{cid}")
	public void removeProductFromCart(@PathVariable("cid") Long cartId) {		
		shoppingCartServices.removeProduct(cartId);
	}
	
//	@PostMapping("/product/post")
//	public Product postProduct(@RequestBody Product product) {
//		return productRepository.save(product);
//	}
	
	@GetMapping("/cart/get")
	public List<CartItem> getAllCartItems(){
		return cartItemRepository.findAll();
	}
	
	@GetMapping("/cart/sum/{uid}")
	public Long getCartSum(@PathVariable("uid") Long userId) {
		Customer customer=userRepository.getReferenceById(userId);
		List<CartItem> cartItems=shoppingCartServices.listCartItems(customer);
		Long sum = 0l;
		for(CartItem c:cartItems) {
			Long sumLong=shoppingCartServices.cartSum(c,userId);
			sum = sum+sumLong;
		}

		return sum;
	}
	
	
	
	
	
}