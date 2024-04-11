package com.store.greenShoes.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.greenShoes.DTO.CartItemDTO;
import com.store.greenShoes.model.CartColorSizeProduct;
import com.store.greenShoes.model.CartItem;
import com.store.greenShoes.model.Color;
import com.store.greenShoes.model.Product;
import com.store.greenShoes.model.ProductSizeColor;
import com.store.greenShoes.model.Size;
import com.store.greenShoes.repository.CartColorSizeProductRepository;
import com.store.greenShoes.repository.CartItemRepository;
import com.store.greenShoes.repository.ColorRepository;
import com.store.greenShoes.repository.ProductRepository;
import com.store.greenShoes.repository.ProductSizeColorRepository;
import com.store.greenShoes.repository.SizeRepository;

@Service
public class ShoppingCartServices {
	
	@Autowired
	private CartItemRepository cartItemRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CartColorSizeProductRepository cartColorSizeProductRepository; 
	@Autowired
	private SizeRepository sizeRepository;
	@Autowired 
	private ColorRepository colorRepository;
	@Autowired
	private ProductSizeColorRepository productSizeColorRepository;
	
//	public List<CartItem> listCartItems(Users customer){
//		return cartItemRepository.findByUser(customer);
//	}
	public CartItemDTO putQuantity(CartItemDTO cartItemDTO) {//edit
		Product product=productRepository.getReferenceById(cartItemDTO.getProductId());
		CartItem cartItem = cartItemRepository.getReferenceById(cartItemDTO.getCartId());
		Size size=sizeRepository.getReferenceById(cartItemDTO.getSizeId());
		Color color=colorRepository.getReferenceById(cartItemDTO.getColorId());
		Long quantity=cartItemDTO.getQuantity();
		ProductSizeColor psc1= productSizeColorRepository.findByProductSizeColor(product, size,color);
		CartColorSizeProduct cartColorSizeProduct=cartColorSizeProductRepository.findByProductSizeColorAndCartItem(psc1,cartItem);
		cartColorSizeProduct=cartColorSizeProductRepository.getReferenceById(cartColorSizeProduct.getId());
		cartColorSizeProduct.setQuantity(quantity);
		cartColorSizeProduct.setSubTotal(quantity*product.getPrice());
		
		CartColorSizeProduct c= cartColorSizeProductRepository.save(cartColorSizeProduct);
		CartItemDTO ctd=new CartItemDTO();
		ctd.setCartId(Optional.of(c.getCartItem().getId()));
		ctd.setColorId(c.getProductSizeColor().getColorId().getID());
		ctd.setProductId(c.getProductSizeColor().getProductId().getId());
		ctd.setQuantity(c.getQuantity());
		ctd.setSizeId(c.getProductSizeColor().getSizeId().getID());
		ctd.setUserId(Optional.of(c.getCartItem().getUser().getUserId()));
		
		return ctd;
		
		
	}

	public void removeProducts(Long cartId) {
		List<CartColorSizeProduct> ccspList=cartColorSizeProductRepository.findByCartItem(cartItemRepository.getReferenceById(cartId));
		for(CartColorSizeProduct ccsp:ccspList) {
			cartColorSizeProductRepository.deleteById(ccsp.getId());
		}
		//cartItemRepository.deleteById(cartId);
	}

	public CartColorSizeProduct addProduct(CartColorSizeProduct cartColorSizeProduct) {
		
		return cartColorSizeProductRepository.save(cartColorSizeProduct);
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

	public void removeSingleProduct(Long id) {
		cartColorSizeProductRepository.deleteById(id);
		
	}
	

}
