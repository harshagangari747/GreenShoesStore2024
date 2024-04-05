package com.store.greenShoes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store.greenShoes.model.CartColorSizeProduct;
import com.store.greenShoes.model.CartItem;
import com.store.greenShoes.model.ProductSizeColor;

public interface CartColorSizeProductRepository extends JpaRepository<CartColorSizeProduct, Long> {


	List<CartColorSizeProduct> findByCartItem(CartItem cartItem);

	CartColorSizeProduct findByProductSizeColorAndCartItem(ProductSizeColor psc1, CartItem cartItem);

	

}
