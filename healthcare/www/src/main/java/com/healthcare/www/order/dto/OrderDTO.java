package com.healthcare.www.order.dto;

import com.healthcare.www.product.dto.ProductDTO;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDTO {

    private Float accrualRate = 0.01f;  // 적립률

    private String orderNo; // 주문번호

    private String userId; // 아이디

    private Long productNo; // 상품번호

    private Integer orderQty; // 수량

    private Integer orderPrice; // 주문 금액

    private Integer point; // 적립금

    private ProductDTO productDTO; // 상품정보 객체

//    public Integer getOrderPrice() {
//        return this.realPrice * this.orderQty;
//    }
//
//    public Integer getPoint() {
//        return ((int)(this.realPrice * accrualRate));
//    }
}

