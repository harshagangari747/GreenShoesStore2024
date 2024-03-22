package com.store.greenShoes.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Category {
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long categoryID;
	private String category;
	public Long getCategoryID() {
		return categoryID;
	}
	public void setCategoryID(Long categoryID) {
		this.categoryID = categoryID;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}

	
}
