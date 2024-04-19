package com.store.greenShoes.DTO;

import java.time.LocalDate;
import java.util.List;

import com.store.greenShoes.model.BillingAddress;
import com.store.greenShoes.model.PaymentInformation;
import com.store.greenShoes.model.ShippingAddress;

public class ResponseOrderDTO {
	private Long OrderId;
	private LocalDate orderDate;
	private Long userId;
	private Long cartId;
	public Long getCartId() {
		return cartId;
	}
	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}
	private List<ResponseProductWithImageDTO> productWithImageDTO;
	public List<ResponseProductWithImageDTO> getProductWithImageDTO() {
		return productWithImageDTO;
	}
	public void setProductWithImageDTO(List<ResponseProductWithImageDTO> productWithImageDTO) {
		this.productWithImageDTO = productWithImageDTO;
	}
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
	
	public LocalDate getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
	
	
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}

}
