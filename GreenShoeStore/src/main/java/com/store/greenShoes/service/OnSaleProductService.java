package com.store.greenShoes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.greenShoes.Constants.Constants;
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
		try {
			Product currProduct = productRepository.getReferenceById(saleProduct.getProductId().getId());
			if (saleProduct.getSalePrice() > currProduct.getPrice())
				throw new Exception(Constants.onSaleProductPriceIsGreaterThanCurrentPriceError);
			OnSaleProducts existingSale = ospRepository.findByProductId(saleProduct.getProductId());
			if (existingSale == null) {
				OnSaleProducts newSaleProduct = new OnSaleProducts();
				newSaleProduct.setCurrentPrice(saleProduct.getCurrentPrice());
				newSaleProduct.setProductId(currProduct);
				newSaleProduct.setSalePrice(saleProduct.getSalePrice());
				if (saleProduct.getCurrentPrice() != currProduct.getPrice())
					throw new Exception(Constants.onSaleProductCurrentPriceMisMatch);
				currProduct.setPrice(saleProduct.getSalePrice());
				productRepository.save(currProduct);
				return (ospRepository.save(saleProduct));
			} else {
				throw new Exception(Constants.onSaleProductAlreadyExists);
			}
		} catch (Exception ex) {
			throw ex;
		}

	}

	public String revertProductFromSale(Long id) throws Exception {
		try {
			Product currProduct = productRepository.getReferenceById(id);
			OnSaleProducts currSaleProduct = ospRepository.findByProductId(currProduct);
			currProduct.setPrice(currSaleProduct.getCurrentPrice());
			productRepository.save(currProduct);
			ospRepository.deleteById(currSaleProduct.getId());
			return Constants.onSaleProductRevertSuccess;
		} catch (Exception ex) {
			throw new Exception(Constants.onSaleProductRevertError);
		}
	}

	public Object updatePrice(OnSaleProducts saleProduct) {
		Product currProduct = productRepository.getReferenceById(saleProduct.getProductId().getId());
		OnSaleProducts onSale= ospRepository.getReferenceById(saleProduct.getId());
		onSale.setSalePrice(saleProduct.getSalePrice());
		currProduct.setPrice(saleProduct.getSalePrice());
		productRepository.save(currProduct);
		return ospRepository.save(onSale);
	}

}
