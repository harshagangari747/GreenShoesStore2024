package com.store.greenShoes.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class OrderDetails {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ManyToOne
	@JoinColumn(name="productSizeColor_id")
	private ProductSizeColor productSizeColor;
	@ManyToOne
	@JoinColumn(name="order_id")
	private Orders order;
	private Long quantity;
	private float price;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public ProductSizeColor getProductSizeColor() {
		return productSizeColor;
	}
	public void setProductSizeColor(ProductSizeColor productSizeColor) {
		this.productSizeColor = productSizeColor;
	}
	public Orders getOrder() {
		return order;
	}
	public void setOrder(Orders order) {
		this.order = order;
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
