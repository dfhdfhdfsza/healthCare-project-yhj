package com.healthcare.www.plan.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QHealthInfo is a Querydsl query type for HealthInfo
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QHealthInfo extends EntityPathBase<HealthInfo> {

    private static final long serialVersionUID = 1531337690L;

    public static final QHealthInfo healthInfo = new QHealthInfo("healthInfo");

    public final StringPath bodypart = createString("bodypart");

    public final StringPath equipment = createString("equipment");

    public final StringPath imgUrl = createString("imgUrl");

    public final NumberPath<Long> infoNo = createNumber("infoNo", Long.class);

    public final StringPath instructions = createString("instructions");

    public final StringPath name = createString("name");

    public final StringPath secondaryMuscles = createString("secondaryMuscles");

    public final StringPath target = createString("target");

    public QHealthInfo(String variable) {
        super(HealthInfo.class, forVariable(variable));
    }

    public QHealthInfo(Path<? extends HealthInfo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QHealthInfo(PathMetadata metadata) {
        super(HealthInfo.class, metadata);
    }

}

