package com.healthcare.www.order.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

import com.querydsl.core.types.Expression;

/**
 * com.healthcare.www.order.dto.QOrderDTO is a Querydsl Projection type for OrderDTO
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QOrderDTO extends ConstructorExpression<OrderDTO> {

    private static final long serialVersionUID = 1666501067L;

    public QOrderDTO(Expression<? extends com.healthcare.www.order.domain.Cart> order) {
        super(OrderDTO.class, new Class<?>[]{com.healthcare.www.order.domain.Cart.class}, order);
    }

    public QOrderDTO(com.healthcare.www.product.dto.QProductDTO productDTO) {
        super(OrderDTO.class, new Class<?>[]{com.healthcare.www.product.dto.ProductDTO.class}, productDTO);
    }

}

