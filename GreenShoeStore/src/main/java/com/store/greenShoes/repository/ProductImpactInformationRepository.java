package com.store.greenShoes.repository;

import com.store.greenShoes.model.ProductImpactInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.*;

public interface ProductImpactInformationRepository extends JpaRepository<ProductImpactInformation, Long> {
	@Query("select i from ProductImpactInformation i where i.product.id=?1")
	List<ProductImpactInformation> getInformationByProductId(Long id);

}
