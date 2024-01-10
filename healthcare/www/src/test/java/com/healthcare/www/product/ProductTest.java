package com.healthcare.www.product;

import com.healthcare.www.product.domain.Product;
import com.healthcare.www.product.repository.ProductRepository;
import org.objectweb.asm.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

@SpringBootTest
public class ProductTest {

    @Autowired
    private ProductRepository productRepository;
    int qty; // 등록개수

    // 상품등록
    public void register(){
        // given
        Product p = Product.builder().
                productName("옷").
                productType("의류").
                build();
        Product p2 = Product.builder().
                productName("신발").
                productType("의류").
                build();
        // when
        productRepository.save(p);
        productRepository.save(p2);
        // then
    }
}
