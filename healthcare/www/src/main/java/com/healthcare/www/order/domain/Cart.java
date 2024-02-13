package com.healthcare.www.order.domain;

import com.healthcare.www.order.dto.OrderDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cart", indexes = @Index(name = "IDX_no_fk_user_id", columnList = "user_id")) // 테이블명 및 인덱스 설정
public class Cart { // 장바구니 domain

    @Id
    @Column(name = "order_no")
    private String orderNo; // 주문번호

    @Column(name = "user_id")
    private String userId; // 아이디

    @Column(name = "product_no")
    private Long productNo; // 상품번호

    @Column(name = "order_qty")
    private Integer orderQty; // 수량

    @Column(name = "order_price")
    private Integer orderPrice; // 주문 금액

    @Column(name = "point")
    private Integer point; // 적립금

    @Column(name = "add_date")
    private LocalDateTime addDate; // 추가 일자

    public Cart(OrderDTO orderDTO) {
        this.orderNo = orderDTO.getOrderNo();
        this.userId = orderDTO.getUserId();
        this.productNo = orderDTO.getProductNo();
        this.orderQty = orderDTO.getOrderQty();
        this.orderPrice = orderDTO.getOrderPrice();
        this.point = orderDTO.getPoint();
        this.addDate = orderDTO.getAddDate();
    }

}
