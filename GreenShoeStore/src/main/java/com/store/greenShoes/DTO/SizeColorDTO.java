package com.store.greenShoes.DTO;

import java.util.List;
import com.store.greenShoes.model.Size;

public class SizeColorDTO {
	private Size size;
	private List<ColorQuantityDTO> color;
	public Size getSize() {
		return size;
	}
	public void setSize(Size size) {
		this.size = size;
	}
	
	
	public List<ColorQuantityDTO> getColor() {
		return color;
	}
	public void setColor(List<ColorQuantityDTO> color) {
		this.color = color;
	}
//	public int getQuantity() {
//		return quantity;
//	}
//	public void setQuantity(int quantity) {
//		this.quantity = quantity;
//	}
//	private int quantity;
	

}
