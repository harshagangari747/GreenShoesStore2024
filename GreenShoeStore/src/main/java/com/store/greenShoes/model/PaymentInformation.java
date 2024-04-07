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
	@Column(length=16)
	private Long ccNumber;
	private Date expirationDate;
	private int cvv;
	@OneToOne
	@JoinColumn(name ="billing_id")
	private BillingAddress billingAddress;
	public BillingAddress getBillingAddress() {
		return billingAddress;
	}
	public void setBillingAddress(BillingAddress billingAddress) {
		this.billingAddress = billingAddress;
	}
	public int getCvv() {
		return cvv;
	}
	public void setCvv(int cvv) {
		this.cvv = cvv;
	}
	public Long getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}
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
