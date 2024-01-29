package com.healthcare.www.product.service;

import com.healthcare.www.product.domain.Product;
import com.healthcare.www.product.dto.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    void addProduct(ProductDTO productDTO); // 상품등록

    void updateProduct(ProductDTO productDTO); // 상품수정

    List<ProductDTO> searchProductList(ProductDTO productDTO); // 상품검색

    List<ProductDTO> getList();

    Page<ProductDTO> getProductListAndPaging(ProductDTO productDTO, Pageable pageable);

    ProductDTO getProduct(Long productNo);
}
