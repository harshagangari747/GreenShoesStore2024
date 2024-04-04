package com.store.greenShoes.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.store.greenShoes.DTO.CartItemDTO;
import com.store.greenShoes.model.CartItem;
import com.store.greenShoes.model.Color;
import com.store.greenShoes.model.Product;
import com.store.greenShoes.model.ProductSizeColor;
import com.store.greenShoes.model.Size;
import com.store.greenShoes.model.Users;
import com.store.greenShoes.repository.CartItemRepository;
import com.store.greenShoes.repository.ColorRepository;
import com.store.greenShoes.repository.ProductRepository;
import com.store.greenShoes.repository.ProductSizeColorRepository;
import com.store.greenShoes.repository.SizeRepository;
import com.store.greenShoes.repository.UsersRepository;
import com.store.greenShoes.service.ShoppingCartServices;

import jakarta.transaction.Transactional;

@RestController
//@CrossOrigin(origins="http://localhost:5923",methods = {RequestMethod.DELETE,RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT})
public class ShoppingCartController {
	
	@Autowired
	private ShoppingCartServices shoppingCartServices;
	
	@Autowired
	private UsersRepository userRepository;
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CartItemRepository cartItemRepository;
	@Autowired
	private SizeRepository sizeRepository;
	@Autowired 
	private ColorRepository colorRepository;
	@Autowired
	private ProductSizeColorRepository productSizeColorRepository;

	
	@GetMapping("/cart/{uid}")
	public List<CartItemDTO> showShoppingCart(@PathVariable("uid") Long customerId) {
		Users customer=userRepository.getReferenceById(customerId);
		List<CartItem> cartItems=shoppingCartServices.listCartItems(customer);
		List<CartItemDTO> cartItemDTO= new ArrayList<>();
		
		for(CartItem c:cartItems) {
			CartItemDTO ctd=new CartItemDTO();
			ctd.setCartId(c.getId());
			ctd.setColorId(c.getProductSizeColor().getColorId().getID());
			ctd.setProductId(c.getProductSizeColor().getProductId().getId());
			ctd.setQuantity(c.getQuantity());
			ctd.setSizeId(c.getProductSizeColor().getSizeId().getID());
			ctd.setUserId(c.getUser().getUserId());
			cartItemDTO.add(ctd);
		}
		return cartItemDTO;
	}
	
	@PostMapping("/cart")
	public CartItem addProduct(@RequestBody CartItemDTO cartItemDTO) {
		Product product=productRepository.getReferenceById(cartItemDTO.getProductId());
		Users customer=userRepository.getReferenceById(cartItemDTO.getUserId());
		Size size=sizeRepository.getReferenceById(cartItemDTO.getSizeId());
		Color color=colorRepository.getReferenceById(cartItemDTO.getColorId());
		Long quantity=cartItemDTO.getQuantity();
		ProductSizeColor psc1= productSizeColorRepository.findByProductSizeColor(product, size,color);
		CartItem cartItem= new CartItem();
		cartItem.setProductSizeColor(psc1);
		cartItem.setQuantity(quantity);
		cartItem.setUser(customer);
		cartItem.setSubTotal(cartItem.getQuantity()*product.getPrice());
		return shoppingCartServices.addProduct(cartItem);
		
	}
	
	@PutMapping("/cart")
	public CartItemDTO addquantity(@RequestBody CartItemDTO cartItemDTO) {
		return shoppingCartServices.putQuantity(cartItemDTO);
	}
	
	
	@Transactional
	@DeleteMapping("/cart/remove/{cid}")
	public void removeProductFromCart(@PathVariable("cid") Long cartId) {		
		shoppingCartServices.removeProduct(cartId);
	}
//	
//
//	
//	@GetMapping("/cart/get")
//	public List<CartItem> getAllCartItems(){
//		return cartItemRepository.findAll();
//	}
//	
//	@GetMapping("/cart/sum/{uid}")
//	public float getCartSum(@PathVariable("uid") Long userId) {
//		Customer customer=userRepository.getReferenceById(userId);
//		List<CartItem> cartItems=shoppingCartServices.listCartItems(customer);
//		float sum = 0;
//		for(CartItem c:cartItems) {
//			float sumLong=shoppingCartServices.cartSum(c,userId);
//			sum = sum+sumLong;
//		}
//
//		return sum;
//	}
//	
	
	
	
	
}