package com.healthcare.www.product;

import com.healthcare.www.product.domain.Product;
import com.healthcare.www.product.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.logging.LogLevel;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ProductTest {

    @Autowired
    private ProductRepository productRepository;

    // 테스트 전 Product 객체 세팅
    List<Product> getProduct(){
        Product p = Product.builder().
                productName("모둠견과류").
                productType("건강식품").
                productInfo("하루 필수 영양소를 골고루 섭취 가능").
                price(20000).
                build();
        Product p2 = Product.builder().
                productName("단백질보충제").
                productType("보충제").
                productInfo("순수 분리유청단백질 첨가된 보충제").
                price(55000).
                build();
        List<Product> productList = new ArrayList<>();
        productList.add(p);
        productList.add(p2);
        return productList;
    }
    // 상품등록 테스트
    @Test
    void register(){
        // given
        List<Product> productList = getProduct();

        // when
        productRepository.save(productList.get(0));
        productRepository.save(productList.get(1));

        // then
        List<Product> findedList = productRepository.findAll();

        assertEquals(2, findedList.size());
    }
    
    // 상품수정 테스트
    @Test
    void update(){
        // given
        List<Product> productList = getProduct();
        productRepository.save(productList.get(0));
        productRepository.save(productList.get(1));
        Product p = Product.builder().
                productNo(1L).
                productName("견과류").
                productType("건강").
                productInfo("하루 필수 영양소를 골고루 섭취 가능").
                price(30000).
                build();
        Product p2 = Product.builder().
                productNo(2L).
                productName("탄수화물보충제").
                productType("보충").
                productInfo("식물성 탄수화물이 첨가된 보충제").
                price(75000).
                build();
        // when
        productRepository.save(p);
        productRepository.save(p2);
        // then
        List<Product> updateList = productRepository.findAll();

        assertEquals(2, updateList.size());
    }

    // 상품삭제 테스트
    @Test
    @DisplayName(value = "상품삭제 테스트")
    void delete(){
        // given
        List<Product> productList = getProduct();
        productRepository.save(productList.get(0));
        productRepository.save(productList.get(1));

        // when
        productRepository.delete(productList.get(0));
        List<Product> deleteList = productRepository.findAll();
        assertEquals(1, deleteList.size());

        productRepository.deleteById(2L);
        List<Product> allDeleteList = productRepository.findAll();

        // then
        assertTrue(allDeleteList.isEmpty());
    }

    // 상품조회
    @Test
    @DisplayName(value = "상품조회")
    void select(){
        // given
        List<Product> productList = getProduct();
        productRepository.save(productList.get(0));
        productRepository.save(productList.get(1));

        // when
        Product p = productRepository.findByProductNameIgnoreCase("모둠견과류");

        // then
        Assertions.assertEquals("모둠견과류", p.getProductName());

    }

}
