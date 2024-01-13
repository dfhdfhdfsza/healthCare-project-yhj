package com.healthcare.www.product;

import com.healthcare.www.product.repository.ProductImageFileRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductImageFileTest {
    @Autowired
    private ProductImageFileRepository productImageFileRepository;

    @Test
    @DisplayName(name = "이미지 파일 저장")
    void save(){
        
    }


}
