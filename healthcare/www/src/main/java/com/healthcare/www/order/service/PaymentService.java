package com.healthcare.www.order.service;

import com.healthcare.www.order.dto.PaymentDTO;

import java.util.List;

public interface PaymentService {

    public void orderSave(String userId, String orderNumber, int point);

    List<PaymentDTO> getOrderHistory(String username);

    void orderCancel(String orderNo, String userId, Integer orderPoint);
}
