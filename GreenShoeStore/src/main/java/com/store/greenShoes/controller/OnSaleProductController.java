package com.store.greenShoes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.store.greenShoes.model.OnSaleProducts;
import com.store.greenShoes.repository.OnSaleProductRepository;
import com.store.greenShoes.service.OnSaleProductService;

@RestController
@CrossOrigin(origins="http://localhost:3000",methods = {RequestMethod.DELETE,RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT})
public class OnSaleProductController {
	@Autowired
	OnSaleProductRepository ospRepository;
	@Autowired
	OnSaleProductService ospService;

	@GetMapping("/saleproduct/getOnSaleProducts")
	private ResponseEntity<List<OnSaleProducts>> getOnSaleProducts() {
		try {
			List<OnSaleProducts> currentOnsaleProducts = ospRepository.findAll();
			return ResponseEntity.ok(currentOnsaleProducts);
		} catch (Exception ex) {
			return ResponseEntity.badRequest().body(null);
		}
	}

	@PostMapping("/saleproduct/postProductAsSale")
	private ResponseEntity<Object> postProductAsSale(@RequestBody OnSaleProducts saleProduct) {
		try {
			return ResponseEntity.ok(ospService.moveProductToSale(saleProduct));

		} catch (Exception ex) {
			return ResponseEntity.badRequest().body(new String("Cant mark product as on sale!"));
		}
	}

}
