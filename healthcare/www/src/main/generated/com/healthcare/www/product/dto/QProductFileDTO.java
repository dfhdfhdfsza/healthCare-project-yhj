package com.healthcare.www.product.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.healthcare.www.product.dto.QProductFileDTO is a Querydsl Projection type for ProductFileDTO
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QProductFileDTO extends ConstructorExpression<ProductFileDTO> {

    private static final long serialVersionUID = 1464620909L;

    public QProductFileDTO(com.querydsl.core.types.Expression<? extends com.healthcare.www.product.domain.ProductFile> productFile) {
        super(ProductFileDTO.class, new Class<?>[]{com.healthcare.www.product.domain.ProductFile.class}, productFile);
    }

}

