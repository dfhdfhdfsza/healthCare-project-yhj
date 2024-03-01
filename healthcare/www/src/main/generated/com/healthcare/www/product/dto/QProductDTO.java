package com.healthcare.www.product.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.healthcare.www.product.dto.QProductDTO is a Querydsl Projection type for ProductDTO
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QProductDTO extends ConstructorExpression<ProductDTO> {

    private static final long serialVersionUID = -419075191L;

    public QProductDTO(com.querydsl.core.types.Expression<? extends com.healthcare.www.product.domain.Product> product) {
        super(ProductDTO.class, new Class<?>[]{com.healthcare.www.product.domain.Product.class}, product);
    }

    public QProductDTO(com.querydsl.core.types.Expression<? extends com.healthcare.www.product.domain.Product> product, com.querydsl.core.types.Expression<? extends java.util.List<ProductFileDTO>> productFileDTOList) {
        super(ProductDTO.class, new Class<?>[]{com.healthcare.www.product.domain.Product.class, java.util.List.class}, product, productFileDTOList);
    }

}

