package com.healthcare.www.order.dto;

import com.healthcare.www.order.domain.Cart;
import com.healthcare.www.product.dto.ProductDTO;
import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDTO implements Serializable {

    private Float accrualRate = 0.01f;  // 적립률

    private String orderNo; // 주문번호

    private String userId; // 아이디

    private Long productNo; // 상품번호

    private Integer orderQty; // 수량

    private Integer orderPrice; // 주문 금액

    private Integer point; // 적립금

    private ProductDTO productDTO; // 상품정보 객체

    private LocalDateTime addDate; // 추가 일자

    @QueryProjection
    public OrderDTO(Cart order){
        this.orderNo = order.getOrderNo();
        this.userId = order.getUserId();
        this.productNo = order.getProductNo();
        this.orderQty = order.getOrderQty();
        this.orderPrice = order.getOrderPrice();
        this.point = order.getPoint();
    }
    @QueryProjection
    public OrderDTO(ProductDTO productDTO){
        this.productDTO = productDTO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDTO orderDTO = (OrderDTO) o;
        return Objects.equals(accrualRate, orderDTO.accrualRate) && Objects.equals(orderNo, orderDTO.orderNo) && Objects.equals(userId, orderDTO.userId) && Objects.equals(productNo, orderDTO.productNo) && Objects.equals(orderQty, orderDTO.orderQty) && Objects.equals(orderPrice, orderDTO.orderPrice) && Objects.equals(point, orderDTO.point) && Objects.equals(productDTO, orderDTO.productDTO);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accrualRate, orderNo, userId, productNo, orderQty, orderPrice, point, productDTO);
    }
}

