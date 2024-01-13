package com.healthcare.www.product.service;

import com.healthcare.www.product.ProductDTO;
import com.healthcare.www.product.domain.ProductImageFile;
import com.healthcare.www.product.repository.ProductImageFileRepository;
import com.healthcare.www.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final ProductImageFileRepository productImageFileRepository;

    @Override
    public void save(ProductDTO productDTO) {
        productRepository.save(productDTO.getProduct());
        for(ProductImageFile productImageFile : productDTO.getProductImageFileList()) {
            ProductImageFile.builder().productNo(productDTO.getProduct().getProductNo());
            productImageFileRepository.save(productImageFile);
        }
    }
}
