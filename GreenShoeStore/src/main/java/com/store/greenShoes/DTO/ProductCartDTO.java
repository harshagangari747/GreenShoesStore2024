package com.store.greenShoes.DTO;

import java.util.List;

import com.store.greenShoes.model.Image;

public class ProductCartDTO {
	private Long productId;
	private Long colorId;
	private Long sizeId;
	private String colorName;
	private String productName;
	private Long quantity;
	private float price;
	private float size;
	private List<Image> images;
	public List<Image> getImages() {
		return images;
	}
	public void setImages(List<Image> images) {
		this.images = images;
	}
	public float getSize() {
		return size;
	}
	public void setSize(float size) {
		this.size = size;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
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
	public Long getQuantity() {
		return quantity;
	}
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	

}
