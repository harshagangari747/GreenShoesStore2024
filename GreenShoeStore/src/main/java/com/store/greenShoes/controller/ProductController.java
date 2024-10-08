package com.store.greenShoes.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.store.greenShoes.DTO.AllProductsDTO;
import com.store.greenShoes.DTO.ProductCartDTO;
import com.store.greenShoes.DTO.ProductDTO;
import com.store.greenShoes.model.Product;
import com.store.greenShoes.service.ProductService;


@RestController
@CrossOrigin(origins = {"http://localhost:3000","http://localhost:3001"}, methods = { RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST,
		RequestMethod.PUT })

public class ProductController {
	@Autowired
	ProductService productService;

	@GetMapping("/product")
	private ResponseEntity<List<AllProductsDTO>> getAllProducts(
			@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(name = "size", required = false, defaultValue = "100000") Integer size) {
		try {
			List<AllProductsDTO> productList = productService.getAllProducts(page, size);
			return ResponseEntity.ok(productList);

		} catch (Exception e) {
			return ResponseEntity.badRequest().body(null);
		}

	}

	@PostMapping(value = "/admin/product", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE })
	private ResponseEntity<Object> postProduct(@RequestPart("image") List<MultipartFile> productImages,
			@RequestPart("product") ProductDTO product) {
		try {
			Product product1 = productService.postProduct(product, productImages);
			return ResponseEntity.ok(product1);

		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new String(e.getMessage()));
		}
	}

//	@PostMapping("/products")
//	private List<Product> postManyProducts(@RequestBody List<Product> products){
//		return productService.postManyProducts(products);
//	}
//	
	@PutMapping("/admin/product/{id}")
	private ProductDTO updateProduct(@PathVariable("id") Long id, @RequestBody ProductDTO product) {
		return productService.updateProduct(id, product);
	}

//	
//	@DeleteMapping("/product/{id}")
//	private void deleteProduct(@PathVariable("id") Long id) {
//		productService.deleteProduct(id);
//	}
//	
	@GetMapping("/product/{id}")
	private ProductDTO getProductById(@PathVariable("id") Long id) {
		return productService.getProductById(id);
	}
	
	@GetMapping("/productByCategory/{categoryId}")
	private List<AllProductsDTO> getProductsByCategory(@RequestParam(name="page", required = false, defaultValue = "0") Integer page,
			@RequestParam(name="size", required = false, defaultValue = "100000") Integer size, @PathVariable("categoryId") Long categoryId){
		return productService.getProductsByCategory(page, size, categoryId);
	}
	
	@GetMapping("/product/productBySizeColor/{pid}/{sid}/{cid}")
	private ProductCartDTO getProductBySizeColor(@PathVariable("pid")Long pid,@PathVariable("sid")Long sid, @PathVariable("cid")Long cid) {
		
		return productService.getProductBySizeColor(pid,sid,cid);
	}
	
	@GetMapping("admin/product/productsWithLowStock")
	private ResponseEntity<List<AllProductsDTO>> getAllLowStockProducts(
			@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(name = "size", required = false, defaultValue = "100000") Integer size) {
		try {
			List<AllProductsDTO> productList = productService.getAllLowStockProducts(page, size);
			return ResponseEntity.ok(productList);

		} catch (Exception e) {
			return ResponseEntity.badRequest().body(null);
		}

	}
	
	@DeleteMapping("admin/deleteProduct/{pid}")
	private ResponseEntity<Object> deleteProduct(@PathVariable("pid") Long pid){
		try {
			productService.deleteProduct(pid);
			return ResponseEntity.ok("Product Deleted Successfully");

		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Failed to Delete Product");
		}
	}
//	
//	@GetMapping("/product/search")
//	private List<Product> searchProduct(@RequestParam(name="q", required = true) String keyword){
//		return productService.searchProduct(keyword);
//	}
//	
//	@GetMapping("/print")
//	private void print() {
//		productService.print();
//	}

}
