package com.store.greenShoes.model;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
@Entity
public class PaymentInformation {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long paymentId;
	@Column(length=16)
	private Long ccNumber;
	private int expYear;
	private int expMonth;
	//private Date expirationDate;
	private int cvv;
	@ManyToOne
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
	public int getExpYear() {
		return expYear;
	}
	public void setExpYear(int expYear) {
		this.expYear = expYear;
	}
	public int getExpMonth() {
		return expMonth;
	}
	public void setExpMonth(int expMonth) {
		this.expMonth = expMonth;
	}
	
//	public Date getExpirationDate() {
//		return expirationDate;
//	}
//	public void setExpirationDate(Date expirationDate) {
//		this.expirationDate = expirationDate;
//	}
	

}
