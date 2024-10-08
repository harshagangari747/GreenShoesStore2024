package com.store.greenShoes.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.store.greenShoes.DTO.CartItemDTO;
import com.store.greenShoes.model.CartColorSizeProduct;
import com.store.greenShoes.model.CartItem;
import com.store.greenShoes.model.Color;
import com.store.greenShoes.model.Product;
import com.store.greenShoes.model.ProductSizeColor;
import com.store.greenShoes.model.Size;
import com.store.greenShoes.model.Users;
import com.store.greenShoes.repository.CartColorSizeProductRepository;
import com.store.greenShoes.repository.CartItemRepository;
import com.store.greenShoes.repository.ColorRepository;
import com.store.greenShoes.repository.ImageRepository;
import com.store.greenShoes.repository.ProductRepository;
import com.store.greenShoes.repository.ProductSizeColorRepository;
import com.store.greenShoes.repository.SizeRepository;
import com.store.greenShoes.repository.UsersRepository;
import com.store.greenShoes.service.ShoppingCartServices;

import jakarta.transaction.Transactional;

@RestController
@CrossOrigin(origins = {"http://localhost:3000","http://localhost:3001"}, methods = { RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST,
		RequestMethod.PUT })

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
	@Autowired
	private CartColorSizeProductRepository cartColorSizeProductRepository; 
	@Autowired
	private ImageRepository imageRepository;

	
	@GetMapping("/user/cart/{uid}")
	public List<CartItemDTO> showShoppingCart(@PathVariable("uid") Long customerId) {
		Users customer=userRepository.getReferenceById(customerId);
		CartItem cartItem = cartItemRepository.findByUser(customer);
		if(cartItem!=null) {
		List<CartColorSizeProduct> cartColorSizeProducts=cartColorSizeProductRepository.findByCartItem(cartItem);
		//List<CartItem> cartItems=shoppingCartServices.listCartItems(customer);
		List<CartItemDTO> cartItemDTO= new ArrayList<>();
		
		for(CartColorSizeProduct c:cartColorSizeProducts) {
			CartItemDTO ctd=new CartItemDTO();
			//ProductSizeColor psc=productSizeColorRepository.findByProductSizeColor(null, null, null)
			ctd.setCartProductSizeColorId(c.getId());
			ctd.setColorName(c.getProductSizeColor().getColorId().getColor());
			ctd.setProductId(c.getProductSizeColor().getProductId().getId());
			ctd.setProductName(c.getProductSizeColor().getProductId().getName());
			ctd.setQuantity(c.getQuantity());
			ctd.setSizeId(c.getProductSizeColor().getSizeId().getID());
			ctd.setUserId(c.getCartItem().getUser().getUserId());
			ctd.setColorId(c.getProductSizeColor().getColorId().getID());
			ctd.setSize(c.getProductSizeColor().getSizeId().getSize());
			ctd.setStockAvailable(c.getProductSizeColor().getQuantity());
			ctd.setImages(imageRepository.getByProductId(c.getProductSizeColor().getProductId().getId()));
			ctd.setPrice(c.getProductSizeColor().getProductId().getPrice());
			ctd.setCartId(cartItem.getId());
			cartItemDTO.add(ctd);
		}
		
		return cartItemDTO;}
		else {
			return null;
		}
	}
	
	@PostMapping("/user/cart")
	public CartColorSizeProduct addProduct(@RequestBody CartItemDTO cartItemDTO) {
		System.out.println("User Id"+cartItemDTO.getUserId());
		Product product=productRepository.getReferenceById(cartItemDTO.getProductId());
		Users customer=null;
		if(cartItemDTO.getUserId()!=null) {
			customer=userRepository.getReferenceByUserId(cartItemDTO.getUserId());
		}
		
		Size size=sizeRepository.getReferenceById(cartItemDTO.getSizeId());
		Color color=colorRepository.getReferenceById(cartItemDTO.getColorId());
		Long quantity=cartItemDTO.getQuantity();
		ProductSizeColor psc1= productSizeColorRepository.findByProductSizeColor(product, size,color);
		CartItem cartItem;
		System.out.println("Cart Id"+cartItemDTO.getCartId());
		if(cartItemDTO.getCartId()==null) {
			System.out.println("User Id"+cartItemDTO.getUserId());
			cartItem= new CartItem();
			cartItem.setUser(customer);
			cartItem=cartItemRepository.save(cartItem);}
		else {
			cartItem= cartItemRepository.getReferenceById(cartItemDTO.getCartId());
		}
		CartColorSizeProduct cartColorSizeProduct=cartColorSizeProductRepository.findByProductSizeColorAndCartItem(psc1,cartItem);
		if(cartColorSizeProduct!=null) {
			cartColorSizeProduct=cartColorSizeProductRepository.getReferenceById(cartColorSizeProduct.getId());
			cartColorSizeProduct.setQuantity(cartColorSizeProduct.getQuantity()+quantity);
			cartColorSizeProduct.setSubTotal(cartColorSizeProduct.getQuantity()*product.getPrice());
		}
		else {
			 cartColorSizeProduct= new CartColorSizeProduct();
			cartColorSizeProduct.setCartItem(cartItem);
			cartColorSizeProduct.setProductSizeColor(psc1);
			cartColorSizeProduct.setQuantity(quantity);
			cartColorSizeProduct.setSubTotal(quantity*product.getPrice());
		}
		return shoppingCartServices.addProduct(cartColorSizeProduct);}
		
	
	
	@PutMapping("/user/cart")
	public CartItemDTO addquantity(@RequestBody CartItemDTO cartItemDTO) {
		return shoppingCartServices.putQuantity(cartItemDTO);
	}
	
	
	@Transactional
	@DeleteMapping("/user/cart/remove/{cid}")
	public void removeProductsFromCart(@PathVariable("cid") Long cartId) {		
		shoppingCartServices.removeProducts(cartId);
	}
	
	@Transactional
	@DeleteMapping("/user/cart/{cartProductId}")
	public void removeSingleProductFromCart(@PathVariable("cartProductId") Long id) {		
		 shoppingCartServices.removeSingleProduct(id);
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