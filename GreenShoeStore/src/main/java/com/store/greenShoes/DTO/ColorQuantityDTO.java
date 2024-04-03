package com.store.greenShoes.DTO;

import com.store.greenShoes.model.Color;

public class ColorQuantityDTO {
	private Color color;
	private Long quantity;
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public Long getQuantity() {
		return quantity;
	}
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	
}
