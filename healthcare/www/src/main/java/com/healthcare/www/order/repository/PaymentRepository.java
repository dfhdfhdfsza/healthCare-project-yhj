package com.healthcare.www.order.repository;

import com.healthcare.www.order.domain.Payment;
import com.healthcare.www.order.dto.PaymentDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, String> {
    List<Payment> findAllByUserId(String username);
}
