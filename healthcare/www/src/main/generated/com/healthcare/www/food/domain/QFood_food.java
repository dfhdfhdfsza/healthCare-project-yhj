package com.healthcare.www.food.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QFood_food is a Querydsl query type for food
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFood_food extends EntityPathBase<Food.food> {

    private static final long serialVersionUID = 738070185L;

    public static final QFood_food food = new QFood_food("food");

    public QFood_food(String variable) {
        super(Food.food.class, forVariable(variable));
    }

    public QFood_food(Path<? extends Food.food> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFood_food(PathMetadata metadata) {
        super(Food.food.class, metadata);
    }

}

