package com.healthcare.www.product.service;

import com.healthcare.www.product.domain.Product;
import com.healthcare.www.product.dto.ProductDTO;

import java.util.List;

public interface ProductService {

    void addProduct(ProductDTO productDTO); // 상품등록

    void updateProduct(ProductDTO productDTO); // 상품수정

    List<Product> searchProductList(ProductDTO productDTO); // 상품검색
}
