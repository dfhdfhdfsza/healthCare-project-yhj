package com.healthcare.www.order.service;

import com.healthcare.www.membership.repository.MembershipQueryRepository;
import com.healthcare.www.membership.repository.MembershipRepository;
import com.healthcare.www.order.domain.Cart;
import com.healthcare.www.order.domain.Payment;
import com.healthcare.www.order.dto.PaymentDTO;
import com.healthcare.www.order.repository.OrderRepository;
import com.healthcare.www.order.repository.PaymentRepository;
import com.healthcare.www.product.dto.ProductDTO;
import com.healthcare.www.product.dto.ProductFileDTO;
import com.healthcare.www.product.repository.ProductFileRepository;
import com.healthcare.www.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor

public class PaymentServiceImpl implements PaymentService{

    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;
    private  final ProductRepository productRepository;
    private  final ProductFileRepository productFileRepository;
    private final MembershipQueryRepository membershipQueryRepository;
    private final MembershipRepository membershipRepository;

    // 주문 결과 저장
    @Override
    public void orderSave(String userId, String orderNumber, int point) {
        List<Cart> cartList = orderRepository.findByUserId(userId);
        List<Payment> payments = cartList.stream().map(Payment::new).toList();
        int pointValue = 0;
        for (Payment payment : payments) {
            pointValue += payment.getPoint();
            payment.setPaymentNo(orderNumber);
            payment.setOrderDate(LocalDateTime.now());
        }
        log.info("적립포인트 >>> " + pointValue + ", 사용포인트 >>> " + point);
        paymentRepository.saveAll(payments); // 주문 저장
        membershipRepository.updateUserIdByPoint(userId, pointValue - point);

    }

    // 주문 목록 요청
    @Override
    public List<PaymentDTO> getOrderHistory(String username) {
        List<Payment> paymenList = paymentRepository.findAllByUserId(username);
        PaymentDTO paymentDTO = new PaymentDTO(paymenList);
        paymentDTO.getPaymentDTOList().forEach(p -> p.setProductDTO(
                new ProductDTO(productRepository.findByProductNo(p.getProductNo()).get(0),
                productFileRepository.findByProductNo(p.getProductNo()).stream().map(ProductFileDTO::new).toList()
                )));
        return paymentDTO.getPaymentDTOList();
    }
    
    // 주문 취소 요청
    @Override
    public void orderCancel(String orderNo, String userId, Integer orderPoint) {
        paymentRepository.deleteById(orderNo);
        // 유저의 적립 포인트도 변경 해야함!! (아직 유저 로직 구현 안함)
        membershipRepository.updatePointByUserId(orderPoint, userId);

    }
}
