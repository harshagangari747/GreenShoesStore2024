package com.store.greenShoes.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Orders {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long orderID;
	private Date orderDate;
	private float totalPrice;
	private int quantity;
	@ManyToOne
	@JoinColumn(name="productSizeColor_Id")
	private ProductSizeColor productSizeColor;
	
	@ManyToOne
	@JoinColumn(name="payment_id")
	private PaymentInformation payment;
	
	@ManyToOne
	@JoinColumn(name="shipping_id")
	private ShippingAddress shippingAddress;
	
	public Long getOrderID() {
		return orderID;
	}
	public void setOrderID(Long orderID) {
		this.orderID = orderID;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public float getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public ProductSizeColor getProductSizeColor() {
		return productSizeColor;
	}
	public void setProductSizeColor(ProductSizeColor productSizeColor) {
		this.productSizeColor = productSizeColor;
	}
	
}
