package com.store.greenShoes.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "product")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	//var productID=567;
	
	private String name;
	
	//private String picture;
	
	private Long price;
	
	@ManyToOne
	@JoinColumn(name = "catagoryID")
	private Category category;
	
	private String description;
	
	private Long quantity;
//	@OneToMany(mappedBy = "productId",cascade = CascadeType.ALL, orphanRemoval = true)
//	private List<Size> sizes= new ArrayList<>();
//	
//	public void addSize(Size size) {
//		size.setProductId(this);
//        this.sizes.add(size);
//    }
//	
//	public void removeSize(Size size) {
//        sizes.remove(size);
//        size.setProductId(null);
//    }

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public Product() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

//	public String getPicture() {
//		return picture;
//	}
//
//	public void setPicture(String picture) {
//		this.picture = picture;
//	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	
	

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

//	public List<Size> getSizes() {
//		return sizes;
//	}
//
//	public void setSizes(List<Size> sizes) {
//		this.sizes = sizes;
//	}

		
	
	
}