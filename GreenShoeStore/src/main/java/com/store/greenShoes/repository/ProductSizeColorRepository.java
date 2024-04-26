package com.store.greenShoes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.store.greenShoes.model.Color;
import com.store.greenShoes.model.Product;
import com.store.greenShoes.model.ProductSizeColor;
import com.store.greenShoes.model.Size;

public interface ProductSizeColorRepository extends JpaRepository<ProductSizeColor, Long> {
	
	//List<ProductSizeColor> getByProductId(Long id);
	
	@Query("select psc from ProductSizeColor psc where psc.productId=?1 and psc.sizeId=?2")
	List<ProductSizeColor> findByProductSize(Product product, Size size);
	
	@Query("select psc from ProductSizeColor psc where psc.productId=?1")
	List<ProductSizeColor> findByProduct(Product product);
	@Query("select psc from ProductSizeColor psc where psc.productId=?1 and psc.sizeId=?2 and psc.colorId=?3" )
	ProductSizeColor findByProductSizeColor(Product product, Size size, Color color);
	
	
	@Query("SELECT psc.productId FROM ProductSizeColor psc JOIN (SELECT SUM(psc2.Quantity) AS totalQuantity, psc2.productId as productsId FROM ProductSizeColor psc2 GROUP BY psc2.productId) p ON psc.productId = p.productsId WHERE p.totalQuantity <= 10 and psc.productId.isAvailable=true GROUP BY psc.productId")
	

	
	//@Query("select psc.productId from ProductSizeColor psc Join (select sum(quantity) as quantity,productId from ProductSizeColor group by productId) p on psc.productId=p.product_id where p.quantity<400 group by psc.productId")
	List<Product> getLowStock();
	
	
	@Query("SELECT SUM(psc2.Quantity) AS totalQuantity FROM ProductSizeColor psc2 where psc2.productId=?1 GROUP BY psc2.productId")
	Long getAvailableStock(Product product);

	//List<ProductSizeColor> findByProductSize(Long id, Long id2);
	

}
