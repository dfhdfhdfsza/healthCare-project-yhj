package com.healthcare.www.plan.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPlanCalendar is a Querydsl query type for PlanCalendar
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPlanCalendar extends EntityPathBase<PlanCalendar> {

    private static final long serialVersionUID = 38720567L;

    public static final QPlanCalendar planCalendar = new QPlanCalendar("planCalendar");

    public final StringPath exerciseName = createString("exerciseName");

    public final NumberPath<Long> planNo = createNumber("planNo", Long.class);

    public QPlanCalendar(String variable) {
        super(PlanCalendar.class, forVariable(variable));
    }

    public QPlanCalendar(Path<? extends PlanCalendar> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPlanCalendar(PathMetadata metadata) {
        super(PlanCalendar.class, metadata);
    }

}

