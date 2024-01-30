package com.healthcare.www.product.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QProduct is a Querydsl query type for Product
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProduct extends EntityPathBase<Product> {

    private static final long serialVersionUID = -602412241L;

    public static final QProduct product = new QProduct("product");

    public final NumberPath<Integer> discountRate = createNumber("discountRate", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> modDate = createDateTime("modDate", java.time.LocalDateTime.class);

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final StringPath productInfo = createString("productInfo");

    public final StringPath productName = createString("productName");

    public final NumberPath<Long> productNo = createNumber("productNo", Long.class);

    public final StringPath productType = createString("productType");

    public final DateTimePath<java.time.LocalDateTime> regDate = createDateTime("regDate", java.time.LocalDateTime.class);

    public QProduct(String variable) {
        super(Product.class, forVariable(variable));
    }

    public QProduct(Path<? extends Product> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProduct(PathMetadata metadata) {
        super(Product.class, metadata);
    }

}

