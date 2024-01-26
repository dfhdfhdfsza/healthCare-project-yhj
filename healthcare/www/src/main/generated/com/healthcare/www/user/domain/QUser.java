package com.healthcare.www.user.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = 1562328093L;

    public static final QUser user = new QUser("user");

    public final StringPath userAddress = createString("userAddress");

    public final NumberPath<Integer> userAge = createNumber("userAge", Integer.class);

    public final StringPath userId = createString("userId");

    public final StringPath userMail = createString("userMail");

    public final StringPath userName = createString("userName");

    public final NumberPath<Long> userNo = createNumber("userNo", Long.class);

    public final StringPath userNumber = createString("userNumber");

    public final StringPath userPassword = createString("userPassword");

    public final StringPath userRole = createString("userRole");

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

