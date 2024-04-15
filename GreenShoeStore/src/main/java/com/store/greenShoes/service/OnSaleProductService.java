package com.store.greenShoes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.greenShoes.model.OnSaleProducts;
import com.store.greenShoes.model.Product;
import com.store.greenShoes.repository.OnSaleProductRepository;
import com.store.greenShoes.repository.ProductRepository;

@Service
public class OnSaleProductService {
	@Autowired
	private OnSaleProductRepository ospRepository;
	@Autowired
	private ProductRepository productRepository;

	public OnSaleProducts moveProductToSale(OnSaleProducts saleProduct) throws Exception {
		Product currProduct = productRepository.getReferenceById(saleProduct.getProductId().getId());
		System.out.println(currProduct.getPrice() + "" + currProduct.getId());
		OnSaleProducts existingSale = ospRepository.findByProductId(saleProduct.getProductId());
		if ( existingSale == null) {
			System.out.println(currProduct.getPrice());
			OnSaleProducts newSaleProduct = new OnSaleProducts();
			newSaleProduct.setCurrentPrice(saleProduct.getCurrentPrice());
			newSaleProduct.setProductId(currProduct);
			newSaleProduct.setSalePrice(saleProduct.getSalePrice());
			System.out.println(saleProduct.getCurrentPrice() != currProduct.getPrice());
			if (saleProduct.getCurrentPrice() != currProduct.getPrice())
				throw new Exception();
			currProduct.setPrice(saleProduct.getSalePrice());
			productRepository.save(currProduct);
			return (ospRepository.save(saleProduct));
		}
		return saleProduct;
	}

}
