package com.healthcare.www.order.repository;

import com.healthcare.www.order.dto.OrderDTO;
import com.healthcare.www.product.dto.ProductDTO;
import com.healthcare.www.product.dto.ProductFileDTO;
import com.healthcare.www.product.repository.ProductFileRepository;
import com.healthcare.www.product.repository.ProductRepository;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.healthcare.www.order.domain.QCart.cart;

@Repository
@RequiredArgsConstructor
@Slf4j
public class OrderQueryRepository {

    private final JPAQueryFactory queryFactory;
    private final ProductRepository productRepository;
    private final ProductFileRepository productFileRepository;

    // 유저 장바구니 목록 가져오기(상품정보 포함)
    public List<OrderDTO> findByUserId(String userId) {
        List<OrderDTO> orderDTOList = queryFactory.select(Projections.bean(OrderDTO.class,
                        cart.orderNo,
                        cart.userId,
                        cart.productNo,
                        cart.orderQty,
                        cart.orderPrice,
                        cart.point)).
                from(cart).
                where(cart.userId.eq(userId))
                .fetch();
        for (OrderDTO orderDTO : orderDTOList) {
            orderDTO.setProductDTO(
                    new ProductDTO(productRepository.findByProductNo(orderDTO.getProductNo()).get(0),
                    productFileRepository.findByProductNo(orderDTO.getProductNo()).stream().map(ProductFileDTO::new).toList()));
        }
        return orderDTOList;
    }


    private BooleanExpression orderPriceEq(Integer orderPrice){
        return orderPrice != null ? cart.orderPrice.eq(orderPrice) : null;
    }
    private BooleanExpression orderQtyEq(Integer orderQty){
        return orderQty != null ? cart.orderPrice.eq(orderQty) : null;
    }
    private BooleanExpression pointEq(Integer point){
        return point != null ? cart.orderPrice.eq(point) : null;
    }
}
