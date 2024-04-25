package com.store.greenShoes.DTO;

import java.util.List;

import com.store.greenShoes.model.Image;
import com.store.greenShoes.model.OnSaleProducts;

public class OnSaleDTO {
	private OnSaleProducts onSaleProducts;
	private List<Image> images;
	public OnSaleProducts getOnSaleProducts() {
		return onSaleProducts;
	}
	public void setOnSaleProducts(OnSaleProducts onSaleProducts) {
		this.onSaleProducts = onSaleProducts;
	}
	public List<Image> getImages() {
		return images;
	}
	public void setImages(List<Image> images) {
		this.images = images;
	}
	

}
