package com.healthcare.www.user.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserInfo is a Querydsl query type for UserInfo
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserInfo extends EntityPathBase<UserInfo> {

    private static final long serialVersionUID = 81575531L;

    public static final QUserInfo userInfo = new QUserInfo("userInfo");

    public final NumberPath<Double> infoBody = createNumber("infoBody", Double.class);

    public final NumberPath<Double> infoHeight = createNumber("infoHeight", Double.class);

    public final NumberPath<Double> infoMetabolic = createNumber("infoMetabolic", Double.class);

    public final NumberPath<Double> infoSkeletal = createNumber("infoSkeletal", Double.class);

    public final NumberPath<Double> infoWeight = createNumber("infoWeight", Double.class);

    public final NumberPath<Long> userNo = createNumber("userNo", Long.class);

    public QUserInfo(String variable) {
        super(UserInfo.class, forVariable(variable));
    }

    public QUserInfo(Path<? extends UserInfo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserInfo(PathMetadata metadata) {
        super(UserInfo.class, metadata);
    }

}

