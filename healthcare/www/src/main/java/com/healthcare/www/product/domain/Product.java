package com.healthcare.www.product.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;

@Entity
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    // 상품 번호(PK)
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) // 테이터베이스에서 자동 생성
    private long productNo;
    // 상품명
    @Column(name = "product_name")
    private String productName;
    // 상품 유형
    @Column(name = "product_type")
    private String productType;
    // 상품 가격
    private int price;
    // 상품 등록일
    @Column(name = "reg_date")
    @CreatedDate
    private LocalDate regDate;
    // 상품 수정일
    @Column(name = "mod_date")
    @LastModifiedDate
    private LocalDate modDate;


}

// 타입 지정용 enum 클래스
@Getter
enum Types {
    HEALTHY("건강식품"), SUPPLEMENT("보충제");
    private String type;
    Types(String type) {
        this.type = type;
    }
}
