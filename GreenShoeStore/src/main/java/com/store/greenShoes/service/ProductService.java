package com.store.greenShoes.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.store.greenShoes.DTO.AllProductsDTO;
import com.store.greenShoes.DTO.ColorQuantityDTO;
import com.store.greenShoes.DTO.ProductCartDTO;
import com.store.greenShoes.DTO.ProductDTO;
import com.store.greenShoes.DTO.SizeColorDTO;
import com.store.greenShoes.model.Color;
import com.store.greenShoes.model.Image;
import com.store.greenShoes.model.Product;
import com.store.greenShoes.model.ProductSizeColor;
import com.store.greenShoes.model.Size;
import com.store.greenShoes.repository.CategoryRepository;
import com.store.greenShoes.repository.ColorRepository;
import com.store.greenShoes.repository.ImageRepository;
import com.store.greenShoes.repository.ProductRepository;
import com.store.greenShoes.repository.ProductSizeColorRepository;
import com.store.greenShoes.repository.SizeRepository;

import jakarta.transaction.Transactional;

@Service
public class ProductService {
	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	ProductRepository productRepository;
	@Autowired
	SizeRepository sizeRepository;
	@Autowired
	ColorRepository colorRepository;
	@Autowired
	ImageRepository imageRepository;
	@Autowired
	ProductSizeColorRepository productSizeColorRepository;
	String projectDirectory = System.getProperty("user.dir");
	private final String FOLDER_PATH = projectDirectory + "/Images/";

	// CRUD
	public List<AllProductsDTO> getAllProducts(Integer page, Integer sizes) {
		PageRequest pageable = PageRequest.of(page, sizes);
		List<AllProductsDTO> allProductsDTO = new ArrayList<>();

		List<Product> products = productRepository.findAll();
		for (Product product : products) {
			AllProductsDTO prodDTO = new AllProductsDTO();
			System.out.println(product.getDescription());
			prodDTO.setCategory(product.getCategory().getCategory());
			prodDTO.setProductId(product.getId());
			prodDTO.setPrice(product.getPrice());
			prodDTO.setName(product.getName());
			List<ProductSizeColor> psc = productSizeColorRepository.findByProduct(product);
			Set<Float> prodSizes=new HashSet<>();
			Set<String> prodColors=new HashSet<>();
			for (ProductSizeColor psc1 : psc) {
				float size = psc1.getSizeId().getSize();
				prodSizes.add(size);
				String color=psc1.getColorId().getColor();
				prodColors.add(color);
				
			}
			prodDTO.setSizes(prodSizes);
			prodDTO.setColor_names(prodColors);
			allProductsDTO.add(prodDTO);
			
//			List<SizeColorDTO> scdList = new ArrayList<>();
//			for (ProductSizeColor psc1 : psc) {
//				Size size = psc1.getSizeId();
//				SizeColorDTO scd = new SizeColorDTO();
//				System.out.println(scd);
//				List<ProductSizeColor> psc2 = productSizeColorRepository.findByProductSize(product, size);
//				List<ColorQuantityDTO> cqdList = new ArrayList<>();
//				for (ProductSizeColor psc3 : psc2) {
//					ColorQuantityDTO cqd = new ColorQuantityDTO();
//					cqd.setColor(psc3.getColorId());
//					cqd.setQuantity(psc3.getQuantity());
//					cqdList.add(cqd);
//				}
//				scd.setColor(cqdList);
//				scd.setSize(size);
//				scdList.add(scd);
//			}
//			List<Image> images = imageRepository.getByProductId(product.getId());
//			prodDTO.setSizeColorDTO(scdList);
//			prodDTO.setProduct(product);
//			prodDTO.setImages(images);
//			DTO.add(prodDTO);
		}
		return allProductsDTO;
	}

	@Transactional
	public Product postProduct(ProductDTO productDTO, List<MultipartFile> productImages) {
		Product product = productDTO.getProduct();
		List<Image> images = productDTO.getImages();
		List<SizeColorDTO> scd = productDTO.getSizeColorDTO();
		//ProductSizeColor psc = new ProductSizeColor();
		Product insertedProduct = productRepository.save(product);
		Long productId = insertedProduct.getId();
		List<Image> imagesUploaded = new ArrayList<Image>();
		for (SizeColorDTO s : scd) {

			Size s1 = s.getSize();
			List<ColorQuantityDTO> colors = s.getColor();
			for (ColorQuantityDTO c : colors) {
				ProductSizeColor psc = new ProductSizeColor();
				psc.setProductId(insertedProduct);
				psc.setColorId(c.getColor());
				psc.setQuantity(c.getQuantity());
				psc.setSizeId(s1);
				productSizeColorRepository.save(psc);
			}

			
		}

		images = new ArrayList<Image>();
		for (MultipartFile file : productImages) {
			try
			{
				String awsS3Url = AwsS3Service.GetAwsServiceObj().UploadImages(file);
				Image imageData = new Image();
				imageData.setProductId(insertedProduct);
				imageData.setURL(awsS3Url);
				images.add(imageData);
				imagesUploaded.add(imageRepository.save(imageData));
			}
			
			catch(Exception e)
			{
				System.out.println(e.getMessage());
				return null;
			}
		}
		return insertedProduct;

	}

	public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
		Product newProduct = productDTO.getProduct();
		Product oldProduct = productRepository.getReferenceById(id);
		oldProduct.setName(newProduct.getName());
		oldProduct.setPrice(newProduct.getPrice());
		oldProduct.setCategory(newProduct.getCategory());
		oldProduct.setDescription(newProduct.getDescription());

		productRepository.save(oldProduct);
		// List<ProductSizeColor>
		// pscList=productSizeColorRepository.findByProduct(oldProduct);
		List<SizeColorDTO> scdList = productDTO.getSizeColorDTO();
		List<Image> images = productDTO.getImages();
		// ProductSizeColor psc=new ProductSizeColor();
		for (SizeColorDTO s : scdList) {

			Size s1 = s.getSize();
			// List<ProductSizeColor>
			// psc2=productSizeColorRepository.findByProductSize(oldProduct, s1);
			List<ColorQuantityDTO> colors = s.getColor();
			for (ColorQuantityDTO c : colors) {
				ProductSizeColor psc2 = productSizeColorRepository.findByProductSizeColor(oldProduct, s1, c.getColor());
				ProductSizeColor psc3;
				if (psc2 != null) {
					psc3 = productSizeColorRepository.getReferenceById(psc2.getId());
					psc3.setQuantity(c.getQuantity());
				} else {
					psc3 = new ProductSizeColor();
					psc3.setProductId(oldProduct);
					psc3.setColorId(c.getColor());
					psc3.setQuantity(c.getQuantity());
					psc3.setSizeId(s1);
				}

				productSizeColorRepository.save(psc3);
			}

		}
//		for(Color c : colors)
//		{
//			//c.setProductId(insertedProduct);
//			colorRepository.save(c);
//		}

		return productDTO;
		
		
	}

//	public void deleteProduct(Long id) {
//		productRepository.deleteById(id);
//	}
//	
	public ProductDTO getProductById(Long id) {
		ProductDTO prodDTO = new ProductDTO();
		Product product = productRepository.getReferenceById(id);
		System.out.println(product.getDescription());
		List<ProductSizeColor> psc = productSizeColorRepository.findByProduct(product);
		List<SizeColorDTO> scdList = new ArrayList<>();
		for (ProductSizeColor psc1 : psc) {
			Size size = psc1.getSizeId();
			SizeColorDTO scd = new SizeColorDTO();
			System.out.println(scd);
			List<ProductSizeColor> psc2 = productSizeColorRepository.findByProductSize(product, size);
			List<ColorQuantityDTO> cqdList = new ArrayList<>();
			for (ProductSizeColor psc3 : psc2) {
				ColorQuantityDTO cqd = new ColorQuantityDTO();
				cqd.setColor(psc3.getColorId());
				cqd.setQuantity(psc3.getQuantity());
				cqdList.add(cqd);
			}
			scd.setColor(cqdList);
			scd.setSize(size);
			scdList.add(scd);

		}
		List<Image> images = imageRepository.getByProductId(id);
		prodDTO.setSizeColorDTO(scdList);
		prodDTO.setProduct(product);
		prodDTO.setImages(images);
//		List<Size> sizes= sizeRepository.getByProductId(id);
//		List<Color> colors = colorRepository.getByProductId(id);

//		Product product=productRepository.getReferenceById(id);
//		ProductDTO DTO=new ProductDTO();
//		DTO.setSizes(sizes);
//		DTO.setColors(colors);
//		DTO.setImages(images);
//		DTO.setProduct(product);
		return prodDTO;
	}
//
	public List<Product> getProductsByCategory(Integer page, Integer size, Long categoryId) {
		PageRequest pageable = PageRequest.of(page, size);
		return productRepository.findByCategory(categoryRepository.getReferenceById(categoryId), pageable);
	}
//
//	public List<Product> searchProduct(String keyword) {	
//		return  productRepository.searchProduct(keyword);
//	}
//	
////	public Long getProductQuantity(Long id) {
////		return productRepository.getReferenceById(id).getQuantity();
////	}
//	public void print() {
//		System.out.println(FOLDER_PATH);
//	}
//	
//	public String uploadImageToFileSystem(MultipartFile file) throws IOException {
//        String filePath=FOLDER_PATH+file.getOriginalFilename();
//
//        file.transferTo(new File(filePath));
//        return filePath;
//    }

	public ProductCartDTO getProductBySizeColor(Long pid, Long sid,Long cid) {
		
		//ProductCartDTO pcDTO=new ProductCartDTO();
		Product product=productRepository.getReferenceById(pid);
		Size size=sizeRepository.getReferenceById(sid);
		Color color=colorRepository.getReferenceById(cid);
		ProductSizeColor psc= productSizeColorRepository.findByProductSizeColor(product,size,color);
		ProductCartDTO productDTO=new ProductCartDTO();
		productDTO.setColorId(cid);
		productDTO.setSizeId(sid);
		productDTO.setProductId(pid);
		productDTO.setColorName(color.getColor());
		productDTO.setSize(size.getSize());
		productDTO.setPrice(product.getPrice());
		productDTO.setQuantity(psc.getQuantity());
		productDTO.setProductName(product.getName());
		return productDTO;
	}


//    public byte[] downloadImageFromFileSystem(String fileName) throws IOException {
//        Optional<Product> fileData = productRepository.findByPicture(fileName);
//        String filePath=fileData.get().getPicture();
//        byte[] images = Files.readAllBytes(new File(filePath).toPath());
//        return images;
//    }

}
