package com.healthcare.www.order.service;

import com.healthcare.www.order.dto.OrderDTO;
import com.healthcare.www.user.domain.User;

import java.util.List;

public interface OrderService {
    List<OrderDTO> addOrderList(OrderDTO orderDTO); // 장바구니 갱신 및 추가

    List<OrderDTO> findAll(String userId); // 장바구니 목록 가져오기

    void deleteCart(String orderNo); // 장바구니 제품 삭제

    int updateCart(OrderDTO orderDTO);

    void deleteCartAll(String username);

    User getUserInfo(String username);
}
