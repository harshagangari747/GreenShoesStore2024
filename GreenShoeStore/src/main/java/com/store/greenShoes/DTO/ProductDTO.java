package com.store.greenShoes.DTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.store.greenShoes.model.Image;
import com.store.greenShoes.model.Product;
import com.store.greenShoes.model.ProductImpactInformation;

public class ProductDTO {
	private Product product;

	private Set<SizeColorDTO> sizeColorDTO;

	private List<Image> images;

	private float price;

	private List<ProductImpactInformation> productEcoImpactInformation;

	public float getPrice() {
		return price;
	}

	public List<ProductImpactInformation> getProductEcoImpactInformation() {
		return productEcoImpactInformation;
	}

	public void setProductEcoImpactInformation(List<ProductImpactInformation> productEcoImpactInformation) {
		this.productEcoImpactInformation = productEcoImpactInformation;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Set<SizeColorDTO> getSizeColorDTO() {
		return sizeColorDTO;
	}

	public void setSizeColorDTO(Set<SizeColorDTO> scdList) {
		this.sizeColorDTO = scdList;
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
