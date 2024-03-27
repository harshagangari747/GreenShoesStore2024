package com.store.greenShoes.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.store.greenShoes.DTO.ProductDTO;
import com.store.greenShoes.model.Color;
import com.store.greenShoes.model.Image;
import com.store.greenShoes.model.Product;
import com.store.greenShoes.model.Size;
import com.store.greenShoes.repository.ColorRepository;
import com.store.greenShoes.repository.ImageRepository;
import com.store.greenShoes.repository.ProductRepository;
import com.store.greenShoes.repository.SizeRepository;

import jakarta.transaction.Transactional;

@Service
public class ProductService {
	@Autowired
	ProductRepository productRepository;
	@Autowired
	SizeRepository sizeRepository;
	@Autowired
	ColorRepository colorRepository;
	@Autowired
	ImageRepository imageRepository;
	String projectDirectory = System.getProperty("user.dir");
	private final String FOLDER_PATH=projectDirectory+"/Images/";
	//CRUD
	public List<ProductDTO> getAllProducts(Integer page, Integer size) {
		PageRequest pageable = PageRequest.of(page, size);
		List<ProductDTO> DTO=new ArrayList<>();
		
		List<Product> products=productRepository.findAll();
		for(Product product:products) {
			List<Size> sizes= sizeRepository.getByProductId(product.getId());
			List<Color> colors = colorRepository.getByProductId(product.getId());
			List<Image> images = imageRepository.getByProductId(product.getId());
			ProductDTO prodDTO=new ProductDTO();
			prodDTO.setProduct(product);
			prodDTO.setSizes(sizes);
			prodDTO.setColors(colors);
			prodDTO.setImages(images);
			DTO.add(prodDTO);
		}
		return DTO;
	}
@Transactional
	public Product postProduct(ProductDTO productDTO)  {
		//product.setId(8942L);
//		for(Size s : product.getSizes())
//		{
//			product.addSize(s);
//		}
		//product.getSizes().forEach(product.addSize(x=>x.size));
		Product product = productDTO.getProduct();
		List<Size> sizes=productDTO.getSizes();
		List<Color> colors=productDTO.getColors();
		List<Image> images=productDTO.getImages();		
		Product insertedProduct=productRepository.save(product);
		//Long productId=insertedProduct.getId();
		for(Size s : sizes)
			{
				s.setProductId(insertedProduct);
				sizeRepository.save(s);
			}
		for(Color c : colors)
		{
			c.setProductId(insertedProduct);
			colorRepository.save(c);
		}
		for(Image i : images)
		{
			i.setProductId(insertedProduct);
			imageRepository.save(i);
		}
		
		return insertedProduct;
		
	}
	
	public List<Product> postManyProducts(List<Product> products){
		return productRepository.saveAll(products);
	}

	public Product updateProduct(Long id, Product product) {
		Product newProduct = productRepository.getReferenceById(id);
		newProduct.setName(product.getName());
		newProduct.setPicture(product.getPicture());
		newProduct.setPrice(product.getPrice());
		newProduct.setCategory(product.getCategory());
		newProduct.setDescription(product.getDescription());
		newProduct.setQuantity(product.getQuantity());
		return productRepository.save(newProduct);
	}

	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
	}
	
	public ProductDTO getProductById(Long id) {
		List<Size> sizes= sizeRepository.getByProductId(id);
		List<Color> colors = colorRepository.getByProductId(id);
		List<Image> images = imageRepository.getByProductId(id);
		Product product=productRepository.getReferenceById(id);
		ProductDTO DTO=new ProductDTO();
		DTO.setSizes(sizes);
		DTO.setColors(colors);
		DTO.setImages(images);
		DTO.setProduct(product);
		return DTO;
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
