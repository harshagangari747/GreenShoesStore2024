package com.store.greenShoes.DTO;

import java.sql.Date;
import java.util.List;

import com.store.greenShoes.model.BillingAddress;
import com.store.greenShoes.model.PaymentInformation;
import com.store.greenShoes.model.ShippingAddress;


public class OrderDTO {
	private Long OrderId;
	private Date orderDate;
	private Long userId;
	private List<ProductSizeColorDTO> productSizeColorList;
	private float total;
	private ShippingAddress shippingAddress;
	private BillingAddress billingAddress;
	private PaymentInformation paymentInformation;
	public ShippingAddress getShippingAddress() {
		return shippingAddress;
	}
	public void setShippingAddress(ShippingAddress shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
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
	public Long getOrderId() {
		return OrderId;
	}
	public void setOrderId(Long orderId) {
		OrderId = orderId;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
	
	public List<ProductSizeColorDTO> getProductSizeColorList() {
		return productSizeColorList;
	}
	public void setProductSizeColorList(List<ProductSizeColorDTO> productSizeColorList) {
		this.productSizeColorList = productSizeColorList;
	}
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
}
