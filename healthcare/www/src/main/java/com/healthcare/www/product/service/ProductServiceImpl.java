package com.healthcare.www.product.service;

import com.healthcare.www.dto.ProductDTO;
import com.healthcare.www.dto.ProductFileDTO;
import com.healthcare.www.product.domain.Product;
import com.healthcare.www.product.domain.ProductFile;
import com.healthcare.www.product.repository.ProductFileRepository;
import com.healthcare.www.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    

}
