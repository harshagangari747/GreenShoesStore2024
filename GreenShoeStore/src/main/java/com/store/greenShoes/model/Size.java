package com.store.greenShoes.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Size {
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long ID;
	private float size;
//	private int quantity;
	
//	@ManyToOne
//	@JoinColumn(name="product_Id")
//	@Schema(description = "Product ID", type = "long", format = "long")
//	private Product productId;
	
	
//	public Product getProductId() {
//		return productId;
//	}
//	public void setProductId(Product productId) {
//		this.productId = productId;
//	}
	public Long getID() {
		return ID;
	}
	public void setID(Long iD) {
		ID = iD;
	}
	
	public float getSize() {
		return size;
	}
	public void setSize(float size) {
		this.size = size;
	}
//	public int getQuantity() {
//		return quantity;
//	}
//	public void setQuantity(int quantity) {
//		this.quantity = quantity;
//	}
}
