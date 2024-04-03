package com.store.greenShoes.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ProductSizeColor {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="product_Id")
	private Product productId;
	
	@ManyToOne
	@JoinColumn(name="color_Id")
	private Color colorId;
	
	@ManyToOne
	@JoinColumn(name="size_Id")
	private Size sizeId;
	
	private Long Quantity;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Product getProductId() {
		return productId;
	}

	public void setProductId(Product productId) {
		this.productId = productId;
	}

	public Color getColorId() {
		return colorId;
	}

	public void setColorId(Color colorId) {
		this.colorId = colorId;
	}

	public Size getSizeId() {
		return sizeId;
	}

	public void setSizeId(Size sizeId) {
		this.sizeId = sizeId;
	}

	public Long getQuantity() {
		return Quantity;
	}

	public void setQuantity(Long quantity) {
		Quantity = quantity;
	}
	

}
