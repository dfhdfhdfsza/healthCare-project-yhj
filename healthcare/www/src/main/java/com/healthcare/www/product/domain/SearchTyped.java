package com.healthcare.www.product.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SearchTyped {
    // 상품 검색 선택용 enum 클래스
    ALL("all", "전체목록"), PRODUCT_NO("productNo","상품번호"), PRODUCT_NAME("productName","상품명"), PRODUCT_TYPE("productType","상품유형");

    private final String type;
    private final String category;

}
