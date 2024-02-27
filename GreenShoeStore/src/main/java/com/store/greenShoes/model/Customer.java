package com.store.greenShoes.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;

@Entity
public class Customer {
	@Id 
	private Long customerId;
	@OneToOne
	@MapsId
	@JoinColumn(referencedColumnName ="userId")
	private UserProfile userProfile;
	@Column(length=10)
	private String mobile;
	@OneToOne
	@JoinColumn(name="addressId")
	private Address address;
	private boolean isAuthenticated;
	@OneToOne
	@JoinColumn(name = "paymentId")
	private PaymentInformation paymentInformation;
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public boolean isAuthenticated() {
		return isAuthenticated;
	}
	public void setAuthenticated(boolean isAuthenticated) {
		this.isAuthenticated = isAuthenticated;
	}
	public PaymentInformation getPaymentInformation() {
		return paymentInformation;
	}
	public void setPaymentInformation(PaymentInformation paymentInformation) {
		this.paymentInformation = paymentInformation;
	}
	
}
