package com.healthcare.www.order.service;

import com.healthcare.www.order.domain.Cart;
import com.healthcare.www.order.dto.OrderDTO;
import com.healthcare.www.order.repository.OrderQueryRepository;
import com.healthcare.www.order.repository.OrderRepository;
import com.healthcare.www.product.dto.ProductDTO;
import com.healthcare.www.product.dto.ProductFileDTO;
import com.healthcare.www.product.repository.ProductFileRepository;
import com.healthcare.www.product.repository.ProductRepository;
import com.healthcare.www.user.domain.User;
import com.healthcare.www.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService{

    private final ProductRepository productRepository;
    private final ProductFileRepository productFileRepository;
    private final OrderRepository orderRepository;
    private final OrderQueryRepository orderQueryRepository;
    private final UserRepository userRepository;
    
    // 장바구니 목록 갱신 및 추가
    @Override
    public List<OrderDTO> addOrderList(OrderDTO orderDTO) {
        boolean checkProduct = false;
        List<OrderDTO> orderDTOList = orderQueryRepository.findByUserId(orderDTO.getUserId());
        for (OrderDTO order : orderDTOList) {
            // 같은 상품이 존재하면 수량, 가격, 포인트만 변경
            if(order.getProductNo().equals(orderDTO.getProductNo())){
                order.setOrderQty(orderDTO.getOrderQty());
                order.setOrderPrice(orderDTO.getOrderPrice());
                order.setPoint(orderDTO.getPoint());
                order.setAddDate(LocalDateTime.now());
                orderRepository.save(new Cart(order));
                checkProduct = true;
                break;
            }
        }
        // 같은 상품이 존재하지 않으면 새로 추가
        if(!checkProduct){
            Cart cart = Cart.builder().
                    orderNo(orderDTO.getUserId() + "_" + UUID.randomUUID()).
                    orderPrice(orderDTO.getOrderPrice()).
                    orderQty(orderDTO.getOrderQty()).
                    point(orderDTO.getPoint()).
                    productNo(orderDTO.getProductNo()).
                    userId(orderDTO.getUserId()).
                    addDate(LocalDateTime.now()).
                    build();
            OrderDTO result = new OrderDTO(orderRepository.save(cart));
            List<ProductFileDTO> productFileDTOList = productFileRepository.findByProductNo(result.getProductNo()).stream().map(ProductFileDTO::new).toList();
            result.setProductDTO(new ProductDTO(productRepository.findByProductNo(result.getProductNo()).get(0), productFileDTOList));
            orderDTOList.add(result);
        }
        log.info("장바구니 수량 >> {} ",orderDTOList.get(0).getOrderQty());
        return orderDTOList;
    }

    // 장바구니 전체 목록 조회
    @Override
    public List<OrderDTO> findAll(String userId) {
        return orderQueryRepository.findByUserId(userId);
    }

    // 장바구니 수량 및 가격 변경
    @Override
    public int updateCart(OrderDTO orderDTO) {
        Optional<Cart> optionalCart = orderRepository.findById(orderDTO.getOrderNo());
        if(optionalCart.isPresent()){
            Cart cart = optionalCart.get();
            cart.setOrderPrice(orderDTO.getOrderPrice());
            cart.setOrderQty(orderDTO.getOrderQty());
            cart.setPoint(orderDTO.getPoint());
            orderRepository.save(cart);
            return 1;
        } else {
            return 0;
        }
    }

    // 장바구니 목록 삭제
    @Override
    public void deleteCart(String orderNo) {
        orderRepository.deleteById(orderNo);
    }

    // 장보구니 비우기
    @Override
    public void deleteCartAll(String username) {
        orderRepository.deleteAllInBatch(orderRepository.findByUserId(username));
    }
    // 유저 정보 조회
    @Override
    public User getUserInfo(String username) {
        return userRepository.findByUserId(username);
    }
        
}
