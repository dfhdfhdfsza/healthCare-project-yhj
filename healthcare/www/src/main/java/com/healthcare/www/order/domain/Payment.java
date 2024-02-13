package com.healthcare.www.order.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(indexes = @Index(name = "IDX_no_fk_order_no", columnList = "payment_no"))
public class Payment {

    @Column(name = "payment_no") // 결제 번호
    private String paymentNo;

    @Id @Column(name = "order_no") // 상품주문 번호
    private String orderNo;

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

    @Column(name = "order_date")
    private LocalDateTime orderDate; // 주문일

    public Payment(Cart cart) {
        this.orderNo = cart.getOrderNo();
        this.userId = cart.getUserId();
        this.productNo = cart.getProductNo();
        this.orderQty = cart.getOrderQty();
        this.orderPrice = cart.getOrderPrice();
        this.point = cart.getPoint();
    }

}
