package com.healthcare.www.user.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserInfo is a Querydsl query type for UserInfo
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserInfo extends EntityPathBase<UserInfo> {

    private static final long serialVersionUID = 81575531L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserInfo userInfo = new QUserInfo("userInfo");

    public final NumberPath<Double> infoBody = createNumber("infoBody", Double.class);

    public final NumberPath<Double> infoHeight = createNumber("infoHeight", Double.class);

    public final NumberPath<Double> infoMetabolic = createNumber("infoMetabolic", Double.class);

    public final NumberPath<Double> infoSkeletal = createNumber("infoSkeletal", Double.class);

    public final NumberPath<Double> infoWeight = createNumber("infoWeight", Double.class);

    public final QUser userNo;

    public QUserInfo(String variable) {
        this(UserInfo.class, forVariable(variable), INITS);
    }

    public QUserInfo(Path<? extends UserInfo> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserInfo(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserInfo(PathMetadata metadata, PathInits inits) {
        this(UserInfo.class, metadata, inits);
    }

    public QUserInfo(Class<? extends UserInfo> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.userNo = inits.isInitialized("userNo") ? new QUser(forProperty("userNo")) : null;
    }

}

