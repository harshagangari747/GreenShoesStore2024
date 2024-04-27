package com.store.greenShoes.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ProductImpactInformation {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	private String ecoImpact;
	@ManyToOne
	@JoinColumn(name="product_id")
	private Product product;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getEcoImpact() {
		return ecoImpact;
	}

	public void setEcoImpact(String ecoImpact) {
		this.ecoImpact = ecoImpact;
	}

}
