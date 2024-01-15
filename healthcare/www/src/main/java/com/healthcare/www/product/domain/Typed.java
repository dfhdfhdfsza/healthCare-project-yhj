package com.healthcare.www.product.domain;

import lombok.Getter;

@Getter
public enum Typed {
    // 상품 유형 선택용 enum 클래스
    HEALTHY("건강식품"), SUPPLEMENT("보충제");

    private String type;

    Typed(String type) {
        this.type = type;
    }
}
