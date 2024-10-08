package com.store.greenShoes.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "product")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	//var productID=567;
	
	private String name;
	
	//private String picture;
	
	private float price;
	
	@ManyToOne
	@JoinColumn(name = "catagoryID")
	private Category category;
	
	private String description;
	private boolean isAvailable;
	
//	@OneToMany(mappedBy = "productId",cascade = CascadeType.ALL, orphanRemoval = true)
//	private List<Size> sizes= new ArrayList<>();
//	
//	public void addSize(Size size) {
//		size.setProductId(this);
//        this.sizes.add(size);
//    }
//	
//	public void removeSize(Size size) {
//        sizes.remove(size);
//        size.setProductId(null);
//    }


	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public Product() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Category getCategory() {
		return category;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


		
	
	
}