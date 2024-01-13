package com.healthcare.www.product.repository;

import com.healthcare.www.product.domain.ProductImageFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductImageFileRepository extends JpaRepository<ProductImageFile, UUID> {

}
