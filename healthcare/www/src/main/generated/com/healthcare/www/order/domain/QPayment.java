package com.healthcare.www.order.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPayment is a Querydsl query type for Payment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPayment extends EntityPathBase<Payment> {

    private static final long serialVersionUID = -153331001L;

    public static final QPayment payment = new QPayment("payment");

    public final DateTimePath<java.time.LocalDateTime> orderDate = createDateTime("orderDate", java.time.LocalDateTime.class);

    public final StringPath orderNo = createString("orderNo");

    public final NumberPath<Integer> orderPrice = createNumber("orderPrice", Integer.class);

    public final NumberPath<Integer> orderQty = createNumber("orderQty", Integer.class);

    public final StringPath paymentNo = createString("paymentNo");

    public final NumberPath<Integer> point = createNumber("point", Integer.class);

    public final NumberPath<Long> productNo = createNumber("productNo", Long.class);

    public final StringPath userId = createString("userId");

    public QPayment(String variable) {
        super(Payment.class, forVariable(variable));
    }

    public QPayment(Path<? extends Payment> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPayment(PathMetadata metadata) {
        super(Payment.class, metadata);
    }

}

