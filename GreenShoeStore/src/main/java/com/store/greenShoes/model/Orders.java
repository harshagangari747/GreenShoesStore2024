package com.store.greenShoes.model;

import java.sql.Date;
import java.time.LocalDate;

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
	private LocalDate orderDate;
	private float totalPrice;
//	@ManyToOne
//	@JoinColumn(name="productSizeColor_Id")
//	private ProductSizeColor productSizeColor;
	
	@ManyToOne
	@JoinColumn(name="payment_id")
	private PaymentInformation payment;
	
	@ManyToOne
	@JoinColumn(name="shipping_id")
	private ShippingAddress shippingAddress;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private Users user;
	
	public PaymentInformation getPayment() {
		return payment;
	}
	public void setPayment(PaymentInformation payment) {
		this.payment = payment;
	}
	public ShippingAddress getShippingAddress() {
		return shippingAddress;
	}
	public void setShippingAddress(ShippingAddress shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	public Long getOrderID() {
		return orderID;
	}
	public void setOrderID(Long orderID) {
		this.orderID = orderID;
	}
	public LocalDate getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(LocalDate now) {
		this.orderDate = now;
	}
	public float getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	
}
