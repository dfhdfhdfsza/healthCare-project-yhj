package com.healthcare.www.product.domain;

import com.healthcare.www.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import javax.naming.Name;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Product { // BaseTimeEntity 를 상속받아서 createdDate, modifiedDate 멤버로 추가
    // 상품 번호(PK)
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) // 테이터베이스에서 자동 생성
    private Long productNo;
    // 상품명
    @Column(name = "product_name")
    private String productName;
    // 상품 유형
    @Column(name = "product_type")
    private String productType;
    // 상품 설명
    @Column(name = "product_info")
    private String productInfo;
    // 상품 가격
    private Integer price;
    // 할인율
    @Column(name = "discount_rate")
    private Integer discountRate;
    // 등록일
    @Column(name = "reg_date")
    private LocalDateTime regDate;
    // 수정일
    @Column(name = "mod_date")
    private LocalDateTime modDate;

}

