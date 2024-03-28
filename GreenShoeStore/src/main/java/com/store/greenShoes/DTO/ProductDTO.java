package com.store.greenShoes.DTO;

import java.util.ArrayList;
import java.util.List;

import com.store.greenShoes.model.Color;
import com.store.greenShoes.model.Image;
import com.store.greenShoes.model.Product;
import com.store.greenShoes.model.Size;

public class ProductDTO {
	private Product product;
	private List<Size> sizes= new ArrayList<>();
	private List<Color> colors;
	private List<Image> images;
//	private Long id;
//	
//	//var productID=567;
//	
//	private String name;
//	
//	private String picture;
//	
//	private Long price;
//	
//	
//	private Long category;
//	
//	private String description;
//	
//	private Long quantity;

//	public Long getId() {
//		return id;
//	}
//	public void setId(Long id) {
//		this.id = id;
//	}
//	public String getName() {
//		return name;
//	}
//	public void setName(String name) {
//		this.name = name;
//	}
//	public String getPicture() {
//		return picture;
//	}
//	public void setPicture(String picture) {
//		this.picture = picture;
//	}
//	public Long getPrice() {
//		return price;
//	}
//	public void setPrice(Long price) {
//		this.price = price;
//	}
//	public Long getCategory() {
//		return category;
//	}
//	public void setCategory(Long category) {
//		this.category = category;
//	}
//	public String getDescription() {
//		return description;
//	}
//	public void setDescription(String description) {
//		this.description = description;
//	}
//	public Long getQuantity() {
//		return quantity;
//	}
//	public void setQuantity(Long quantity) {
//		this.quantity = quantity;
//	}
	
	public List<Color> getColors() {
		return colors;
	}
	public void setColors(List<Color> colors) {
		this.colors = colors;
	}
	public List<Image> getImages() {
		return images;
	}
	public void setImages(List<Image> images) {
		this.images = images;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public List<Size> getSizes() {
		return sizes;
	}
	public void setSizes(List<Size> sizes) {
		this.sizes = sizes;
	}
	
	

}
