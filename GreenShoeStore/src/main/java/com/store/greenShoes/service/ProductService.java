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
import com.store.greenShoes.model.ProductImpactInformation;
import com.store.greenShoes.model.ProductSizeColor;
import com.store.greenShoes.model.Size;
import com.store.greenShoes.repository.CategoryRepository;
import com.store.greenShoes.repository.ColorRepository;
import com.store.greenShoes.repository.ImageRepository;
import com.store.greenShoes.repository.ProductImpactInformationRepository;
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
	@Autowired
	ProductImpactInformationRepository productImpactInfoRepository;
	String projectDirectory = System.getProperty("user.dir");
	private final String FOLDER_PATH = projectDirectory + "/Images/";

	// CRUD
	public List<AllProductsDTO> getAllProducts(Integer page, Integer sizes) {
		PageRequest pageable = PageRequest.of(page, sizes);
		List<AllProductsDTO> allProductsDTO = new ArrayList<>();

		List<Product> products = productRepository.findAllAvailableProducts();
		for (Product product : products) {
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
		Set<SizeColorDTO> scd = productDTO.getSizeColorDTO();
		product.setAvailable(true);
		List<ProductImpactInformation> productEcoImpact = productDTO.getProductEcoImpactInformation();
		Product insertedProduct = productRepository.save(product);
		Long productId = insertedProduct.getId();
		List<Image> imagesUploaded = new ArrayList<Image>();
		for (SizeColorDTO s : scd) {
			Size s1 = s.getSize();
			Set<ColorQuantityDTO> colors = s.getColor();
			for (ColorQuantityDTO c : colors) {
				ProductSizeColor psc = new ProductSizeColor();
				psc.setProductId(insertedProduct);
				psc.setColorId(c.getColor());
				psc.setQuantity(c.getQuantity());
				psc.setSizeId(s1);
				ProductSizeColor psc1 = productSizeColorRepository.findByProductSizeColor(insertedProduct, s1,
						c.getColor());
				if (psc1 == null) {
					productSizeColorRepository.save(psc);
				}
			}
		}

		images = new ArrayList<Image>();
		for (MultipartFile file : productImages) {
			try {
				String awsS3Url = AwsS3Service.GetAwsServiceObj().UploadImages(file);
				Image imageData = new Image();
				imageData.setProductId(insertedProduct);
				imageData.setURL(awsS3Url);
				images.add(imageData);
				imagesUploaded.add(imageRepository.save(imageData));
			}

			catch (Exception e) {
				System.out.println(e.getMessage());
				return null;
			}
		}
		
		for(ProductImpactInformation ecoImpactInfo : productEcoImpact)
		{
			ecoImpactInfo.setProduct(insertedProduct);
			productImpactInfoRepository.save(ecoImpactInfo);
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
		Set<SizeColorDTO> scdList = productDTO.getSizeColorDTO();
		List<Image> images = productDTO.getImages();
		// ProductSizeColor psc=new ProductSizeColor();
		for (SizeColorDTO s : scdList) {

			Size s1 = s.getSize();
			// List<ProductSizeColor>
			// psc2=productSizeColorRepository.findByProductSize(oldProduct, s1);
			Set<ColorQuantityDTO> colors = s.getColor();
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
		List<ProductImpactInformation> productEcoImpact = productDTO.getProductEcoImpactInformation();
		for(ProductImpactInformation ecoImpactInfo : productEcoImpact)
		{
			ProductImpactInformation pei=productImpactInfoRepository.getReferenceById(ecoImpactInfo.getId());
			pei.setEcoImpact(ecoImpactInfo.getEcoImpact());
			productImpactInfoRepository.save(ecoImpactInfo);
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
		Set<SizeColorDTO> scdList = new HashSet<>();
		Set<Float> sizes = new HashSet<>();
		for (ProductSizeColor psc1 : psc) {
			Size size = psc1.getSizeId();
			SizeColorDTO scd = new SizeColorDTO();
			System.out.println(scd);
			if (sizes.contains(size.getSize())) {
				System.out.println(size.getSize());
				continue;
			}
			List<ProductSizeColor> psc2 = productSizeColorRepository.findByProductSize(product, size);
			Set<ColorQuantityDTO> cqdList = new HashSet<>();
			for (ProductSizeColor psc3 : psc2) {
				ColorQuantityDTO cqd = new ColorQuantityDTO();
				cqd.setColor(psc3.getColorId());
				cqd.setQuantity(psc3.getQuantity());
				cqdList.add(cqd);
			}
			scd.setColor(cqdList);
			scd.setSize(size);
			scdList.add(scd);
			sizes.add(size.getSize());

		}
		List<Image> images = imageRepository.getByProductId(id);
		List<ProductImpactInformation> ecoImpactInfo = productImpactInfoRepository.getInformationByProductId(id);
		prodDTO.setSizeColorDTO(scdList);
		prodDTO.setProduct(product);
		prodDTO.setImages(images);
		prodDTO.setPrice(product.getPrice());
		prodDTO.setProductEcoImpactInformation(ecoImpactInfo);

		return prodDTO;
	}

//
	public List<AllProductsDTO> getProductsByCategory(Integer page, Integer sizes, Long categoryId) {
//		PageRequest pageable = PageRequest.of(page, size);
//		return productRepository.findByCategory(categoryRepository.getReferenceById(categoryId), pageable);
		PageRequest pageable = PageRequest.of(page, sizes);
		List<AllProductsDTO> allProductsDTO = new ArrayList<>();

		List<Product> products = productRepository.findByCategory(categoryRepository.getReferenceById(categoryId),
				pageable);
		for (Product product : products) {
			AllProductsDTO prodDTO = new AllProductsDTO();
			System.out.println(product.getDescription());
			prodDTO.setCategory(product.getCategory().getCategory());
			prodDTO.setProductId(product.getId());
			prodDTO.setPrice(product.getPrice());
			prodDTO.setName(product.getName());
			List<ProductSizeColor> psc = productSizeColorRepository.findByProduct(product);
			Set<Float> prodSizes = new HashSet<>();
			Set<String> prodColors = new HashSet<>();
			List<ProductImpactInformation> ecoImpactInfo = productImpactInfoRepository.getInformationByProductId(product.getId());
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

		return allProductsDTO;
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

	public ProductCartDTO getProductBySizeColor(Long pid, Long sid, Long cid) {

		// ProductCartDTO pcDTO=new ProductCartDTO();
		Product product = productRepository.getReferenceById(pid);
		Size size = sizeRepository.getReferenceById(sid);
		Color color = colorRepository.getReferenceById(cid);
		ProductSizeColor psc = productSizeColorRepository.findByProductSizeColor(product, size, color);
		ProductCartDTO productDTO = new ProductCartDTO();
		productDTO.setColorId(cid);
		productDTO.setSizeId(sid);
		productDTO.setProductId(pid);
		productDTO.setColorName(color.getColor());
		productDTO.setSize(size.getSize());
		productDTO.setPrice(product.getPrice());
		productDTO.setQuantity(psc.getQuantity());
		productDTO.setProductName(product.getName());
		productDTO.setImages(imageRepository.getByProductId(pid));
		return productDTO;
	}

	public List<AllProductsDTO> getAllLowStockProducts(Integer page, Integer sizes) {
		PageRequest pageable = PageRequest.of(page, sizes);
		List<AllProductsDTO> allProductsDTO = new ArrayList<>();

		List<Product> products = productSizeColorRepository.getLowStock();
		System.out.println(products.size());
		for (Product product : products) {
			AllProductsDTO prodDTO = new AllProductsDTO();
			System.out.println(product.getDescription());
			prodDTO.setCategory(product.getCategory().getCategory());
			prodDTO.setProductId(product.getId());
			prodDTO.setPrice(product.getPrice());
			prodDTO.setName(product.getName());
			Long stockAvailable = productSizeColorRepository.getAvailableStock(product);
			prodDTO.setStockAvailable(stockAvailable);
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

	public void deleteProduct(Long pid) throws Exception {
		try {
			Product product = productRepository.getReferenceById(pid);
			product.setAvailable(false);
			productRepository.save(product);

		} catch (Exception ex) {
			throw new Exception();
		}
	}

}
//    public byte[] downloadImageFromFileSystem(String fileName) throws IOException {
//        Optional<Product> fileData = productRepository.findByPicture(fileName);
//        String filePath=fileData.get().getPicture();
//        byte[] images = Files.readAllBytes(new File(filePath).toPath());
//        return images;
//    }
