package com.store.greenShoes.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.store.greenShoes.model.Product;
import com.store.greenShoes.repository.ProductRepository;
@Service
public class ProductService {
	@Autowired
	ProductRepository productRepository;
	String projectDirectory = System.getProperty("user.dir");
	private final String FOLDER_PATH=projectDirectory+"/Images/";
	//CRUD
	public List<Product> getAllProducts(Integer page, Integer size) {
		PageRequest pageable = PageRequest.of(page, size);
		return productRepository.findAll(pageable).getContent();
	}

	public Product postProduct(Product product, List<MultipartFile> files )  {
		String picture="";
		for(MultipartFile file: files) {
		try {
			picture= this.uploadImageToFileSystem(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}}
		product.setPicture(picture);
		return productRepository.save(product);
	}
	
	public List<Product> postManyProducts(List<Product> products){
		return productRepository.saveAll(products);
	}

	public Product updateProduct(Long id, Product product) {
		Product newProduct = productRepository.getReferenceById(id);
		newProduct.setName(product.getName());
		newProduct.setPicture(product.getPicture());
		newProduct.setPrice(product.getPrice());
		newProduct.setRating(product.getRating());
		newProduct.setVendorName(product.getVendorName());
		newProduct.setCategory(product.getCategory());
		newProduct.setDescription(product.getDescription());
		newProduct.setQuantity(product.getQuantity());
		return productRepository.save(newProduct);
	}

	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
	}
	
	public Product getProductById(Long id) {
		return productRepository.getReferenceById(id);
	}

	public List<Product> getProductsByCategory(Integer page, Integer size, String category) {
		PageRequest pageable = PageRequest.of(page, size);
		return productRepository.findByCategory(category, pageable);
	}

	public List<Product> searchProduct(String keyword) {	
		return  productRepository.searchProduct(keyword);
	}
	
	public Long getProductQuantity(Long id) {
		return productRepository.getReferenceById(id).getQuantity();
	}
	public void print() {
		System.out.println(FOLDER_PATH);
	}
	
	public String uploadImageToFileSystem(MultipartFile file) throws IOException {
        String filePath=FOLDER_PATH+file.getOriginalFilename();

        file.transferTo(new File(filePath));
        return filePath;

    }

    public byte[] downloadImageFromFileSystem(String fileName) throws IOException {
        Optional<Product> fileData = productRepository.findByPicture(fileName);
        String filePath=fileData.get().getPicture();
        byte[] images = Files.readAllBytes(new File(filePath).toPath());
        return images;
    }

}
