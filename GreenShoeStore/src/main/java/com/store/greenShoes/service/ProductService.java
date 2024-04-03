package com.store.greenShoes.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.store.greenShoes.DTO.ColorQuantityDTO;
import com.store.greenShoes.DTO.ProductDTO;
import com.store.greenShoes.DTO.SizeColorDTO;
import com.store.greenShoes.model.Image;
import com.store.greenShoes.model.Product;
import com.store.greenShoes.model.ProductSizeColor;
import com.store.greenShoes.model.Size;
import com.store.greenShoes.repository.ColorRepository;
import com.store.greenShoes.repository.ImageRepository;
import com.store.greenShoes.repository.ProductRepository;
import com.store.greenShoes.repository.ProductSizeColorRepository;
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
	@Autowired
	ProductSizeColorRepository productSizeColorRepository;
	String projectDirectory = System.getProperty("user.dir");
	private final String FOLDER_PATH=projectDirectory+"/Images/";
	//CRUD
	public List<ProductDTO> getAllProducts(Integer page, Integer sizes) {
		PageRequest pageable = PageRequest.of(page, sizes);
		List<ProductDTO> DTO=new ArrayList<>();
		
		List<Product> products=productRepository.findAll();
		for(Product product:products) {
			ProductDTO prodDTO=new ProductDTO();
			System.out.println(product.getDescription());
			List<ProductSizeColor> psc=productSizeColorRepository.findByProduct(product);
			List<SizeColorDTO> scdList=new ArrayList<>();
			for(ProductSizeColor psc1: psc) {
				Size size=psc1.getSizeId();
				SizeColorDTO scd=new SizeColorDTO();
				System.out.println(scd);
				List<ProductSizeColor> psc2=productSizeColorRepository.findByProductSize(product, size);
				List<ColorQuantityDTO> cqdList=new ArrayList<>();
				for(ProductSizeColor psc3:psc2) {
					ColorQuantityDTO cqd=new ColorQuantityDTO();
					cqd.setColor(psc3.getColorId());
					cqd.setQuantity(psc3.getQuantity());
					cqdList.add(cqd);
				}
				scd.setColor(cqdList);
				scd.setSize(size);
				scdList.add(scd);
			}
			List<Image> images = imageRepository.getByProductId(product.getId());
			prodDTO.setSizeColorDTO(scdList);
			prodDTO.setProduct(product);
			prodDTO.setImages(images);
			DTO.add(prodDTO);
		}
					return DTO;
	}
@Transactional
	public Product postProduct(ProductDTO productDTO)  {
		
		//product.getSizes().forEach(product.addSize(x=>x.size));
		Product product = productDTO.getProduct();
//		List<Size> sizes=productDTO.getSizes();
//		List<Color> colors=productDTO.getColors();
		List<Image> images=productDTO.getImages();
		List<SizeColorDTO> scd=productDTO.getSizeColorDTO();
		ProductSizeColor psc=new ProductSizeColor();
		Product insertedProduct=productRepository.save(product);
		Long productId=insertedProduct.getId();
		for(SizeColorDTO s : scd)
			{
			
			Size s1= s.getSize();
			List<ColorQuantityDTO> colors=s.getColor();
			for(ColorQuantityDTO c: colors) {
				psc.setProductId(insertedProduct);
				psc.setColorId(c.getColor());
				psc.setQuantity(c.getQuantity());
				psc.setSizeId(s1);
			}
				
				productSizeColorRepository.save(psc);
			}
//		for(Color c : colors)
//		{
//			//c.setProductId(insertedProduct);
//			colorRepository.save(c);
//		}
		for(Image i : images)
		{
			i.setProductId(insertedProduct);
			imageRepository.save(i);
		}
		
		return insertedProduct;
		
	}

	public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
		Product newProduct=productDTO.getProduct();
		Product oldProduct = productRepository.getReferenceById(id);
		oldProduct.setName(newProduct.getName());
		oldProduct.setPrice(newProduct.getPrice());
		oldProduct.setCategory(newProduct.getCategory());
		oldProduct.setDescription(newProduct.getDescription());
		
		productRepository.save(oldProduct);
		//List<ProductSizeColor> pscList=productSizeColorRepository.findByProduct(oldProduct);
		List<SizeColorDTO> scdList=productDTO.getSizeColorDTO();
		List<Image> images=productDTO.getImages();
		//ProductSizeColor psc=new ProductSizeColor();
		for(SizeColorDTO s : scdList)
			{
			
			Size s1= s.getSize();
			//List<ProductSizeColor> psc2=productSizeColorRepository.findByProductSize(oldProduct, s1);
			List<ColorQuantityDTO> colors=s.getColor();
			for(ColorQuantityDTO c: colors) {
				ProductSizeColor psc2=productSizeColorRepository.findByProductSizeColor(oldProduct, s1,c.getColor());
				ProductSizeColor psc3;
				if(psc2!=null) {
					psc3=productSizeColorRepository.getReferenceById(psc2.getId());
					psc3.setQuantity(c.getQuantity());
				}
				else {
					 psc3=new ProductSizeColor();
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
		ProductDTO prodDTO=new ProductDTO();
		Product product = productRepository.getReferenceById(id);
		System.out.println(product.getDescription());
		List<ProductSizeColor> psc=productSizeColorRepository.findByProduct(product);
		List<SizeColorDTO> scdList=new ArrayList<>();
		for(ProductSizeColor psc1: psc) {
			Size size=psc1.getSizeId();
			SizeColorDTO scd=new SizeColorDTO();
			System.out.println(scd);
			List<ProductSizeColor> psc2=productSizeColorRepository.findByProductSize(product, size);
			List<ColorQuantityDTO> cqdList=new ArrayList<>();
			for(ProductSizeColor psc3:psc2) {
				ColorQuantityDTO cqd=new ColorQuantityDTO();
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
//	public List<Product> getProductsByCategory(Integer page, Integer size, String category) {
//		PageRequest pageable = PageRequest.of(page, size);
//		return productRepository.findByCategory(category, pageable);
//	}
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

//    public byte[] downloadImageFromFileSystem(String fileName) throws IOException {
//        Optional<Product> fileData = productRepository.findByPicture(fileName);
//        String filePath=fileData.get().getPicture();
//        byte[] images = Files.readAllBytes(new File(filePath).toPath());
//        return images;
//    }

}
