package com.healthcare.www.plan.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QExerciseSet is a Querydsl query type for ExerciseSet
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QExerciseSet extends EntityPathBase<ExerciseSet> {

    private static final long serialVersionUID = -1319527750L;

    public static final QExerciseSet exerciseSet = new QExerciseSet("exerciseSet");

    public final BooleanPath exerciseCheck = createBoolean("exerciseCheck");

    public final NumberPath<Integer> exerciseCount = createNumber("exerciseCount", Integer.class);

    public final NumberPath<Long> exerciseSetNo = createNumber("exerciseSetNo", Long.class);

    public final NumberPath<Integer> exerciseWeight = createNumber("exerciseWeight", Integer.class);

    public final NumberPath<Long> planNo = createNumber("planNo", Long.class);

    public QExerciseSet(String variable) {
        super(ExerciseSet.class, forVariable(variable));
    }

    public QExerciseSet(Path<? extends ExerciseSet> path) {
        super(path.getType(), path.getMetadata());
    }

    public QExerciseSet(PathMetadata metadata) {
        super(ExerciseSet.class, metadata);
    }

}

