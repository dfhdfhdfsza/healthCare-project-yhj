package com.healthcare.www.product.repository;

import com.healthcare.www.product.domain.ProductFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductFileRepository extends JpaRepository<ProductFile, Long> {

    List<ProductFile> findByProductNo(Long productNo);
}
