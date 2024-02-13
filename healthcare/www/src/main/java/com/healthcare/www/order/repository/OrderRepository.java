package com.healthcare.www.order.repository;

import com.healthcare.www.order.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Cart, String> {

    List<Cart> findByUserId(String username);
}
