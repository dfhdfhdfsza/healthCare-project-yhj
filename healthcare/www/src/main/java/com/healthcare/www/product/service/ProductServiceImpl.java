package com.healthcare.www.product.service;

import com.healthcare.www.product.domain.Product;
import com.healthcare.www.product.domain.ProductFile;
import com.healthcare.www.product.domain.SearchTyped;
import com.healthcare.www.product.dto.ProductDTO;
import com.healthcare.www.product.dto.ProductFileDTO;
import com.healthcare.www.product.repository.ProductFileRepository;
import com.healthcare.www.product.repository.ProductRepository;
import com.healthcare.www.product.repository.QueryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final ProductFileRepository productFileRepository;
    private final QueryRepository queryRepository;

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
        log.info("product >>>>>> {}", product);
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

    // 상품검색 메서드(관리자 상품 수정용)
    @Override
    public List<Product> searchProductList(ProductDTO productDTO) {
        // enum 필드값과 일치하는지 확인
        for(SearchTyped typed : SearchTyped.values()){
            if(typed.getCategory().equals(productDTO.getCategory())){
                String type = typed.getType();
                if(type.equals(SearchTyped.PRODUCT_NO.getType())){
                    return productRepository.findByProductNo(Long.valueOf(productDTO.getKeyword()));
                } else if (type.equals(SearchTyped.PRODUCT_NAME.getType())) {
                    return productRepository.findByProductNameIgnoreCaseContaining(productDTO.getKeyword());
                } else if (type.equals(SearchTyped.PRODUCT_TYPE.getType())) {
                    return productRepository.findByProductTypeContaining(productDTO.getKeyword());
                } else if (type.equals(SearchTyped.ALL.getType())) {
                    return productRepository.findAll();
                }
            }
        }
        return Collections.emptyList();
    }

    // 상품목록 조회 메서드
    @Override
    public List<ProductDTO> getList() {
        List<Product> productList = productRepository.findAll();
        List<ProductFile> productFileList = productFileRepository.findAll();
        List<ProductDTO> productDTOList;
        for(Product product : productList){
            ProductDTO productDTO = ProductDTO.builder().build();
            productDTOList.add()
        }
        return productRepository.findAll();
    }


}
