package com.healthcare.www.order.dto;

import com.healthcare.www.order.domain.Payment;
import com.healthcare.www.product.dto.ProductDTO;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO{

    private String paymentNo; // 결제 번호

    private String orderNo; // 상품주문 번호

    private String userId; // 아이디

    private Long productNo; // 상품번호

    private Integer orderQty; // 수량

    private Integer orderPrice; // 주문 금액

    private Integer point; // 적립금

    private LocalDateTime orderDate; // 주문일

    private ProductDTO productDTO;

    private List<PaymentDTO> paymentDTOList;

    public PaymentDTO(Payment payment){
        this.paymentNo = payment.getPaymentNo();
        this.orderNo = payment.getOrderNo();
        this.userId = payment.getUserId();
        this.productNo = payment.getProductNo();
        this.orderQty = payment.getOrderQty();
        this.orderPrice = payment.getOrderPrice();
        this.point = payment.getPoint();
        this.orderDate = payment.getOrderDate();
    }
    public PaymentDTO(List<Payment> payment){
        this.paymentDTOList = payment.stream().map(PaymentDTO::new).toList();
    }
}
