package com.store.greenShoes.controller;

import java.util.ArrayList;
import java.util.List;
import com.store.greenShoes.Constants.Constants;
import com.store.greenShoes.DTO.OnSaleDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.store.greenShoes.model.OnSaleProducts;
import com.store.greenShoes.repository.ImageRepository;
import com.store.greenShoes.repository.OnSaleProductRepository;
import com.store.greenShoes.repository.ProductRepository;
import com.store.greenShoes.service.OnSaleProductService;

@RestController
@CrossOrigin(origins = "http://localhost:3000", methods = { RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST,
		RequestMethod.PUT })
@RequestMapping("/product")
public class OnSaleProductController {
	@Autowired
	OnSaleProductRepository ospRepository;
	@Autowired
	OnSaleProductService ospService;
	@Autowired
	ImageRepository imageRepository;
	@Autowired
	ProductRepository productRepository;

	@GetMapping("/getOnSaleProducts")
	private ResponseEntity<List<OnSaleDTO>> getOnSaleProducts() {
		try {
			List<OnSaleProducts> currentOnsaleProducts = ospRepository.findAll();
			List<OnSaleDTO> osDTO=new ArrayList<>();
			for(OnSaleProducts osp:currentOnsaleProducts) {
				if(osp.getProductId().isAvailable()) {
				OnSaleDTO osd=new OnSaleDTO();
				osd.setOnSaleProducts(osp);
				osd.setImages(imageRepository.getByProductId(osp.getProductId().getId()));
				osDTO.add(osd);
				}
			}
			return ResponseEntity.ok(osDTO);
		} catch (Exception ex) {
			return ResponseEntity.badRequest().body(null);
		}
	}

	@PostMapping("admin/postProductAsSale")
	private ResponseEntity<Object> postProductAsSale(@RequestBody OnSaleProducts saleProduct) {
		try {
			return ResponseEntity.ok(ospService.moveProductToSale(saleProduct));

		} catch (Exception ex) {
			return ResponseEntity.badRequest().body(ex);
		}
	}
	@PutMapping("admin/updateSalePrice")
	ResponseEntity<Object> updatePrice(@RequestBody OnSaleProducts saleProduct) {
		try {
			return ResponseEntity.ok(ospService.updatePrice(saleProduct));

		} catch (Exception ex) {
			return ResponseEntity.badRequest().body(ex);
		}
	}

	@DeleteMapping("admin/removeProductFromSale/{productId}")
	private ResponseEntity<Object> removeProductFromSale(@PathVariable("productId") Long id) {
		try {
			return ResponseEntity.ok(ospService.revertProductFromSale(id));
		} catch (Exception ex) {
			return ResponseEntity.badRequest().body(ex);
		}
	}

}
