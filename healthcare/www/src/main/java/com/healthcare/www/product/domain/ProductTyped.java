package com.healthcare.www.product.domain;

import lombok.Getter;

@Getter
public enum ProductTyped {
    // 상품 유형 선택용 enum 클래스
    PROTEIN("프로틴"), GAYNOR("게이너"), ORGANIC("유기농"), DIET("다이어트"), ENERGY("에너지"), NUTRIENTS("영양제"), SHAKER("쉐이커"), FITNESS("헬스용품");

    private final String type;

    ProductTyped(String type) {
        this.type = type;
    }
}
