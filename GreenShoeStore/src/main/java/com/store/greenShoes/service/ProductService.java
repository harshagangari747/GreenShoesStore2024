package com.store.greenShoes.service;

import java.io.File;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
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
	private final String FOLDER_PATH = projectDirectory + "/Images/";

	// CRUD
	public List<ProductDTO> getAllProducts(Integer page, Integer size) {
		PageRequest pageable = PageRequest.of(page, size);
		List<ProductDTO> DTO = new ArrayList<>();

		List<Product> products = productRepository.findAll();
		for (Product product : products) {
			List<Size> sizes = sizeRepository.getByProductId(product.getId());
			List<Color> colors = colorRepository.getByProductId(product.getId());
			List<Image> images = imageRepository.getByProductId(product.getId());
			ProductDTO prodDTO = new ProductDTO();
			prodDTO.setProduct(product);
			prodDTO.setSizes(sizes);
			prodDTO.setColors(colors);
			prodDTO.setImages(images);
			DTO.add(prodDTO);
		}
		return DTO;
	}

	@Transactional
	public ProductDTO postProduct(List<MultipartFile> images, ProductDTO productDTO) {

		try {
			Product product = productDTO.getProduct();
			List<Size> sizes = productDTO.getSizes();
			List<Color> colors = productDTO.getColors();
			List<Image> imagesList = productDTO.getImages();

			Product insertedProduct = productRepository.save(product);
			List<Size> sizeUploaded = new ArrayList<Size>();
			List<Color> colorsUploaded = new ArrayList<Color>();
			List<Image> imagesUploaded = new ArrayList<Image>();
			for (Size s : sizes) {
				s.setProductId(insertedProduct);
				sizeUploaded.add(sizeRepository.save(s));
			}
			for (Color c : colors) {
				c.setProductId(insertedProduct);
				colorsUploaded.add(colorRepository.save(c));
			}

			imagesList = new ArrayList<Image>();
			for (MultipartFile file : images) {
				String awsS3Url = AwsS3Service.GetAwsServiceObj().UploadImages(file);
				Image imageData = new Image();
				imageData.setProductId(insertedProduct);
				imageData.setURL(awsS3Url);
				imagesList.add(imageData);
				imagesUploaded.add(imageRepository.save(imageData));
			}

			ProductDTO p = new ProductDTO();
			p.setProduct(insertedProduct);
			p.setColors(colorsUploaded);
			p.setImages(imagesUploaded);
			p.setSizes(sizeUploaded);
			return p;

		} catch (Exception ex) {
			return null;
		}

	}

	public List<Product> postManyProducts(List<Product> products) {
		return productRepository.saveAll(products);
	}

	public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
		Product newProduct = productDTO.getProduct();
		Product oldProduct = productRepository.getReferenceById(id);
		oldProduct.setName(newProduct.getName());
		oldProduct.setPrice(newProduct.getPrice());
		oldProduct.setCategory(newProduct.getCategory());
		oldProduct.setDescription(newProduct.getDescription());

		productRepository.save(oldProduct);
		List<Size> newSizes = productDTO.getSizes();
		for (Size s : newSizes) {
			Size oldSize = sizeRepository.getReferenceById(s.getID());
			oldSize.setQuantity(s.getQuantity());
			sizeRepository.save(oldSize);
		}
		List<Color> newColor = productDTO.getColors();
		for (Color s : newColor) {
			Color oldColor = colorRepository.getReferenceById(s.getID());
			oldColor.setQuantity(s.getQuantity());
			colorRepository.save(oldColor);
		}
		return productDTO;
	}

	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
	}

	public ProductDTO getProductById(Long id) {
		List<Size> sizes = sizeRepository.getByProductId(id);
		List<Color> colors = colorRepository.getByProductId(id);
		List<Image> images = imageRepository.getByProductId(id);
		Product product = productRepository.getReferenceById(id);
		ProductDTO DTO = new ProductDTO();
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
		return productRepository.searchProduct(keyword);
	}

//	public Long getProductQuantity(Long id) {
//		return productRepository.getReferenceById(id).getQuantity();
//	}
	public void print() {
		System.out.println(FOLDER_PATH);
	}

	public String uploadImageToFileSystem(MultipartFile file) throws IOException {
		String filePath = FOLDER_PATH + file.getOriginalFilename();

		file.transferTo(new File(filePath));
		return filePath;
	}

//    public byte[] downloadImageFromFileSystem(String fileName) throws IOException {
//        Optional<Product> fileData = productRepository.findByPicture(fileName);
//        String filePath=fileData.get().getPicture();
//        byte[] images = Files.readAllBytes(new File(filePath).toPath());
//        return images;
//    }

}
