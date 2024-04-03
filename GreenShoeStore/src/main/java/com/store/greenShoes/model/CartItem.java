package com.store.greenShoes.model;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;



@Entity
@Table(name="cart_items")
public class CartItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
//	@ManyToOne
//	@JoinColumn(name="product_id")
//	private Product product;
	
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Users user;
	
//	@ManyToOne
//	@JoinColumn(name="size_id")
//	private Size size;
//	
//	@ManyToOne
//	@JoinColumn(name="color_id")
//	private Color color;
	

	private Long quantity;
	
	private float subTotal;
	@ManyToOne
	@JoinColumn(name="productSizeColor_id")
	private ProductSizeColor productSizeColor;

	

	public ProductSizeColor getProductSizeColor() {
		return productSizeColor;
	}

	public void setProductSizeColor(ProductSizeColor productSizeColor) {
		this.productSizeColor = productSizeColor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	

	

	public float getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(float subTotal) {
		this.subTotal = subTotal;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	

	

	


	
	

	

}