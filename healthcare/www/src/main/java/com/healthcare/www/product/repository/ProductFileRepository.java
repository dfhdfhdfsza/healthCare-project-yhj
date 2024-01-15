package com.healthcare.www.product.repository;

import com.healthcare.www.product.domain.ProductFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductFileRepository extends JpaRepository<ProductFile, Long> {

}
