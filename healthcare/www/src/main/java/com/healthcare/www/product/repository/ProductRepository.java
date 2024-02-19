package com.healthcare.www.product.repository;

import com.healthcare.www.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByProductNameIgnoreCaseContaining(String keyword);

    List<Product> findByProductTypeContaining(String keyword);

    List<Product> findByProductNo(Long productNo);
}
