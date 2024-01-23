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

    public final com.healthcare.www.QBaseTimeEntity _super = new com.healthcare.www.QBaseTimeEntity(this);

    //inherited
    public final DatePath<java.time.LocalDate> createdDate = _super.createdDate;

    //inherited
    public final DatePath<java.time.LocalDate> modifiedDate = _super.modifiedDate;

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final StringPath productInfo = createString("productInfo");

    public final StringPath productName = createString("productName");

    public final NumberPath<Long> productNo = createNumber("productNo", Long.class);

    public final StringPath productType = createString("productType");

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

