package com.store.greenShoes.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Color {
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long ID;
	private String Color;
//	private int Quantity;
//public int getQuantity() {
//		return Quantity;
//	}
//	public void setQuantity(int quantity) {
//		Quantity = quantity;
//	}
	//	public Long getQuantity() {
//		return Quantity;
//	}
//	public void setQuantity(Long quantity) {
//		Quantity = quantity;
//	}
//	@ManyToOne
//	@JoinColumn(name="product_Id")
//	@Schema(description = "Product ID", type = "long", format = "long")
//	private Product productId;
	public Long getID() {
		return ID;
	}
	public void setID(Long iD) {
		ID = iD;
	}
	public String getColor() {
		return Color;
	}
	public void setColor(String color) {
		Color = color;
	}
//	public Product getProductId() {
//		return productId;
//	}
//	public void setProductId(Product productId) {
//		this.productId = productId;
//	}
	
	
}
