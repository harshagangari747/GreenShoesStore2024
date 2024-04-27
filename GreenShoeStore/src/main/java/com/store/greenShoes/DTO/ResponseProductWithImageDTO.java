package com.store.greenShoes.DTO;

import java.util.List;
import java.util.Optional;

import com.store.greenShoes.model.Image;

public class ResponseProductWithImageDTO {
	private Long productId;
	private float size;
	private String color;
	private Long quantity;
	private float price;
	 public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	private Optional<Image> image;
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	
	public float getSize() {
		return size;
	}
	public void setSize(float size) {
		this.size = size;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Long getQuantity() {
		return quantity;
	}
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	public Optional<Image> getImage() {
		return image;
	}
	public void setImage(Optional<Image> image) {
		this.image = image;
	}
	
	
	
	
}
