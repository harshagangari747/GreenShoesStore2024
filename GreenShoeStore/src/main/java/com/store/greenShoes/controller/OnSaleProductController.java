package com.store.greenShoes.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.store.greenShoes.Constants.Constants;
import com.store.greenShoes.DTO.AllProductsDTO;
import com.store.greenShoes.DTO.OnSaleDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.store.greenShoes.model.Image;
import com.store.greenShoes.model.OnSaleProducts;
import com.store.greenShoes.model.Product;
import com.store.greenShoes.model.ProductSizeColor;
import com.store.greenShoes.repository.ImageRepository;
import com.store.greenShoes.repository.OnSaleProductRepository;
import com.store.greenShoes.repository.ProductRepository;
import com.store.greenShoes.repository.ProductSizeColorRepository;
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
	@Autowired
	ProductSizeColorRepository productSizeColorRepository;

	@GetMapping("/getOnSaleProducts")
	private ResponseEntity<List<AllProductsDTO>> getOnSaleProducts() {
		try {
			List<OnSaleProducts> currentOnsaleProducts = ospRepository.findAll();
			List<AllProductsDTO> allProductsDTO=new ArrayList<>();
			for(OnSaleProducts osp:currentOnsaleProducts) {
				if(osp.getProductId().isAvailable()) {
					Product product= osp.getProductId();
					AllProductsDTO prodDTO = new AllProductsDTO();
					System.out.println(product.getDescription());
					prodDTO.setCategory(product.getCategory().getCategory());
					prodDTO.setProductId(product.getId());
					prodDTO.setPrice(product.getPrice());
					prodDTO.setName(product.getName());
					List<ProductSizeColor> psc = productSizeColorRepository.findByProduct(product);
					Set<Float> prodSizes = new HashSet<>();
					Set<String> prodColors = new HashSet<>();
					for (ProductSizeColor psc1 : psc) {
						float size = psc1.getSizeId().getSize();
						prodSizes.add(size);
						String color = psc1.getColorId().getColor();
						prodColors.add(color);

					}
					prodDTO.setSizes(prodSizes);
					prodDTO.setColor_names(prodColors);
					Image image = imageRepository.getByProductId(product.getId()).get(0);
					prodDTO.setImage(image);
					allProductsDTO.add(prodDTO);
				}
				
			}
			return ResponseEntity.ok(allProductsDTO);
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

	@DeleteMapping("admin/removeProductFromSale")
	private ResponseEntity<Object> removeProductFromSale(@RequestBody Long id) {
		try {
			return ResponseEntity.ok(ospService.revertProductFromSale(id));
		} catch (Exception ex) {
			return ResponseEntity.badRequest().body(ex);
		}
	}

}
