package com.store.greenShoes.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
@Entity
public class PaymentInformation {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long paymentId;
//	@OneToOne
//	@JoinColumn(name = "customerId")
//	private Customer customer;
	@Column(length=16)
	private Long ccNumber;
	private Date expirationDate;
	public Long getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}
//	public Customer getCustomer() {
//		return customer;
//	}
//	public void setCustomer(Customer customer) {
//		this.customer = customer;
//	}
	public Long getCcNumber() {
		return ccNumber;
	}
	public void setCcNumber(Long ccNumber) {
		this.ccNumber = ccNumber;
	}
	public Date getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}
	

}
