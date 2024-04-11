package com.store.greenShoes.DTO;

import java.util.Optional;

public class CartItemDTO {
	private Long cartProductSizeColorId;
	public Long getCartProductSizeColorId() {
		return cartProductSizeColorId;
	}
	public void setCartProductSizeColorId(Long cartProductSizeColorId) {
		this.cartProductSizeColorId = cartProductSizeColorId;
	}
	private Optional<Long> cartId;
	
	public Optional<Long> getCartId() {
		return cartId;
	}
	public void setCartId(Optional<Long> cartId) {
		this.cartId = cartId;
	}
	private Long productId;
	private Optional<Long> userId;
	private Long quantity;
	private Long colorId;
	private Long sizeId;
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	
	public Optional<Long> getUserId() {
		return userId;
	}
	public void setUserId(Optional<Long> userId) {
		this.userId = userId;
	}
	public Long getQuantity() {
		return quantity;
	}
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	public Long getColorId() {
		return colorId;
	}
	public void setColorId(Long colorId) {
		this.colorId = colorId;
	}
	public Long getSizeId() {
		return sizeId;
	}
	public void setSizeId(Long sizeId) {
		this.sizeId = sizeId;
	}
	

}
