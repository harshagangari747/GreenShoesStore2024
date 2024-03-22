package com.store.greenShoes.model;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;



@Entity
@Table(name="cart_items")
public class CartItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="product_id")
	private Product product;
	
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer user;
	
	private Long quantity;
	
	private Long subTotal;

	

	public void setSubTotal(Long subTotal) {
		this.subTotal = subTotal;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public Long getSubTotal() {
		return subTotal;
	}

	

	public Customer getUser() {
		return user;
	}

	public void setUser(Customer user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "CartItem [id=" + id + ", product=" + product + ", user=" + user + ", quantity=" + quantity
				+ ", subTotal=" + subTotal + "]";
	}

	
	

	

}