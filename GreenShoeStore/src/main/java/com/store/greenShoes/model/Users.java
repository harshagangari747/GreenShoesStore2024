package com.store.greenShoes.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;


@Entity
public class Users {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long userId;
	@Column(unique = true)
	private String userName;
	private String password;
	private String role;
	@Column(unique = true)
	private String email;
	@Column(nullable = false)
	private String firstName;
	@Column(nullable = false)
	private String lastName;
	
	@Column(length=10)
	private String mobile;
	
	@OneToOne
	@JoinColumn(name="shipping_id")
	private ShippingAddress shippingAddress;
	@OneToOne
	@JoinColumn(name="billing_id")
	private BillingAddress billingAddress;
	@OneToOne
	@JoinColumn(name="paymentInfo_id")
	private PaymentInformation paymentInformation;
	public BillingAddress getBillingAddress() {
		return billingAddress;
	}
	public void setBillingAddress(BillingAddress billingAddress) {
		this.billingAddress = billingAddress;
	}
	public PaymentInformation getPaymentInformation() {
		return paymentInformation;
	}
	public void setPaymentInformation(PaymentInformation paymentInformation) {
		this.paymentInformation = paymentInformation;
	}
	public ShippingAddress getShippingAddress() {
		return shippingAddress;
	}
	public void setShippingAddress(ShippingAddress shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

}
