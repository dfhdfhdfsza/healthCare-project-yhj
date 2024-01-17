package com.healthcare.www.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO {
    // 상품번호
    private long productNo;
    // 상품명
    @NotEmpty(message = "상품명을 입력하세요.")
    private String productName;
    // 상품 유형
    @NotEmpty(message = "상품유형을 선택하세요.")
    private String productType;
    // 상품 정보
    @NotEmpty(message = "상품설명을 입력하세요.")
    private String productInfo;
    // 상품 가격
    @NotNull
    private Integer price;
    // 첨부파일 리스트
    private List<ProductFileDTO> productFileList;
}
