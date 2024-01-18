package com.healthcare.www.product.service;

import com.healthcare.www.product.domain.Product;
import com.healthcare.www.product.domain.ProductFile;
import com.healthcare.www.product.domain.SearchTyped;
import com.healthcare.www.product.dto.ProductDTO;
import com.healthcare.www.product.dto.ProductFileDTO;
import com.healthcare.www.product.repository.ProductFileRepository;
import com.healthcare.www.product.repository.ProductRepository;
import com.querydsl.core.QueryFactory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Where;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final ProductFileRepository productFileRepository;

    // 상품을 DB에 등록하는 메서드
    @Override
    public void addProduct(ProductDTO productDTO) {
        Product product = Product.builder().
                productName(productDTO.getProductName()).
                productInfo(productDTO.getProductInfo()).
                productType(productDTO.getProductType()).
                price(productDTO.getPrice()).
                build();
        long resultProductNo = productRepository.save(product).getProductNo(); // 상품정보 DB 등록

        List<ProductFile> productFileList  = new ArrayList<>(); // 첨부파일 domain 객체 리스트
        log.info("product file list >>>>>>> {}", productDTO.getProductFileList());
        if(productDTO.getProductFileList() != null) { // 첨부파일이 존재하면
            for (ProductFileDTO file : productDTO.getProductFileList()){
                ProductFile productFile = ProductFile.builder().
                        productNo(resultProductNo).
                        productFileName(file.getProductFileName()).
                        productFileType(file.getProductFileType()).
                        productFileSize(file.getProductFileSize()).
                        productFileSaveDir(file.getProductFileSaveDir()).
                        productUUID(file.getProductUUID()).
                        build();
                productFileList.add(productFile);
            }
            productFileRepository.saveAll(productFileList); // 상품 첨부파일 DB 등록
        }
    }

    // 상품정보 DB에 수정하는 메서드
    @Override
    public void updateProduct(ProductDTO productDTO) {
        Product product = Product.builder().
                productNo(productDTO.getProductNo()).
                productName(productDTO.getProductName()).
                productInfo(productDTO.getProductInfo()).
                productType(productDTO.getProductType()).
                price(productDTO.getPrice()).
                build();
        long resultProductNo = productRepository.save(product).getProductNo(); // 상품정보 DB 수정

        List<ProductFile> productFileList  = new ArrayList<>(); // 첨부파일 domain 객체 리스트
        log.info("product file list >>>>>>> {}", productDTO.getProductFileList());
        if(productDTO.getProductFileList() != null) { // 첨부파일이 존재하면
            for (ProductFileDTO file : productDTO.getProductFileList()){
                ProductFile productFile = ProductFile.builder().
                        productNo(resultProductNo).
                        productFileName(file.getProductFileName()).
                        productFileType(file.getProductFileType()).
                        productFileSize(file.getProductFileSize()).
                        productFileSaveDir(file.getProductFileSaveDir()).
                        productUUID(file.getProductUUID()).
                        build();
                productFileList.add(productFile);
            }
            productFileRepository.saveAll(productFileList); // 상품 첨부파일 DB 수정
        }
    }

    @Override
    public List<ProductDTO> searchProductList(ProductDTO productDTO) {
        // 해당하는 카테고리의 enum 필드 가져오기
        SearchTyped searchTyped =SearchTyped.valueOf(productDTO.getCategory());
        // 가져온 필드의 type값 가져오기 => PRODUCT_NO("productNo", "상품번호") 중 productNo = this.type
        String type = searchTyped.getType();
        return null;
    }









}
