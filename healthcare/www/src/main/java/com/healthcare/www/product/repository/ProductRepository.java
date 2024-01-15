package com.healthcare.www.product.repository;

import com.healthcare.www.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findByProductNameIgnoreCase(String productName);
}
