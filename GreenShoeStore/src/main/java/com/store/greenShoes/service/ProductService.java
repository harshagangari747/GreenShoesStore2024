package com.store.greenShoes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.store.greenShoes.model.Product;
import com.store.greenShoes.repository.ProductRepository;
@Service
public class ProductService {
	@Autowired
	ProductRepository productRepository;

	//CRUD
	public List<Product> getAllProducts(Integer page, Integer size) {
		PageRequest pageable = PageRequest.of(page, size);
		return productRepository.findAll(pageable).getContent();
	}

	public Product postProduct(Product product) {
		return productRepository.save(product);
	}
	
	public List<Product> postManyProducts(List<Product> products){
		return productRepository.saveAll(products);
	}

	public Product updateProduct(Long id, Product product) {
//		Product newProduct = productRepository.getById(id);
//		newProduct.setName(product.getName());
//		newProduct.setPicture(product.getPicture());
//		newProduct.setPrice(product.getPrice());
//		newProduct.setRating(product.getRating());
//		newProduct.setVendorName(product.getVendorName());
//		newProduct.setCategory(product.getCategory());
//		newProduct.setDescription(product.getDescription());
//		newProduct.setQuantity(product.getQuantity());
//		return productRepository.save(newProduct);
		return new Product();
	}

	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
	}
	
	//others
	@SuppressWarnings("deprecation")
	public Product getProductById(Long id) {
		return productRepository.getById(id);
	}

	public List<Product> getProductsByCategory(Integer page, Integer size, String category) {
		PageRequest pageable = PageRequest.of(page, size);
		return productRepository.findByCategory(category, pageable);
	}

	public List<Product> searchProduct(String keyword) {	
		return  productRepository.searchProduct(keyword);
	}
	
	public Long getProductQuantity(Long id) {
		//return productRepository.getById(id).getQuantity();
		return 1L;
	}

}
