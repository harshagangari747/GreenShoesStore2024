package com.store.greenShoes.DTO;

import java.util.List;

public class AllProductsDTO {
	private Long productId;
	private String name;
	private float price;
	private String Category;
	private List<String> color_names;
	private List<Float> sizes;
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getCategory() {
		return Category;
	}
	public void setCategory(String category) {
		Category = category;
	}
	public List<String> getColor_names() {
		return color_names;
	}
	public void setColor_names(List<String> color_names) {
		this.color_names = color_names;
	}
	public List<Float> getSizes() {
		return sizes;
	}
	public void setSizes(List<Float> sizes) {
		this.sizes = sizes;
	}
	

}
