package com.store.greenShoes.DTO;

import java.util.ArrayList;
import java.util.List;

import com.store.greenShoes.model.Image;
import com.store.greenShoes.model.Product;

public class ProductDTO {
	private Product product;

	private List<SizeColorDTO> sizeColorDTO = new ArrayList<>();

	private List<Image> images;

	public List<SizeColorDTO> getSizeColorDTO() {
		return sizeColorDTO;
	}

	public void setSizeColorDTO(List<SizeColorDTO> sizeColorDTO) {
		this.sizeColorDTO = sizeColorDTO;
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
