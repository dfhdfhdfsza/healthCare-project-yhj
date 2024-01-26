package com.healthcare.www.plan.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserPlan is a Querydsl query type for UserPlan
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserPlan extends EntityPathBase<UserPlan> {

    private static final long serialVersionUID = -1977677756L;

    public static final QUserPlan userPlan = new QUserPlan("userPlan");

    public final StringPath planDate = createString("planDate");

    public final NumberPath<Long> planNo = createNumber("planNo", Long.class);

    public final NumberPath<Long> userNo = createNumber("userNo", Long.class);

    public final NumberPath<Long> userPlanNo = createNumber("userPlanNo", Long.class);

    public QUserPlan(String variable) {
        super(UserPlan.class, forVariable(variable));
    }

    public QUserPlan(Path<? extends UserPlan> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserPlan(PathMetadata metadata) {
        super(UserPlan.class, metadata);
    }

}

