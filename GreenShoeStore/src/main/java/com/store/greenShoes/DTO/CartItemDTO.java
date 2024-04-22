package com.store.greenShoes.DTO;

import java.util.List;
import java.util.Optional;

import com.store.greenShoes.model.Image;

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
	private String colorName;
	private String productName;
	private float size;
	private Long stockAvailable;
	private List<Image> images;
	private float price;
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public List<Image> getImages() {
		return images;
	}
	public void setImages(List<Image> images) {
		this.images = images;
	}
	public String getColorName() {
		return colorName;
	}
	public void setColorName(String colorName) {
		this.colorName = colorName;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public float getSize() {
		return size;
	}
	public void setSize(float size) {
		this.size = size;
	}
	public Long getStockAvailable() {
		return stockAvailable;
	}
	public void setStockAvailable(Long stockAvailable) {
		this.stockAvailable = stockAvailable;
	}
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
