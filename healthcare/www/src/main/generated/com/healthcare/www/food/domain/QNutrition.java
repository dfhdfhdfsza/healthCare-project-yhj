package com.healthcare.www.food.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QNutrition is a Querydsl query type for Nutrition
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QNutrition extends EntityPathBase<Nutrition> {

    private static final long serialVersionUID = 1628522067L;

    public static final QNutrition nutrition = new QNutrition("nutrition");

    public final NumberPath<Long> carbohydrate = createNumber("carbohydrate", Long.class);

    public final StringPath date = createString("date");

    public final StringPath eatTime = createString("eatTime");

    public final NumberPath<Long> energyKcal = createNumber("energyKcal", Long.class);

    public final NumberPath<Long> fat = createNumber("fat", Long.class);

    public final NumberPath<Long> nutritionNo = createNumber("nutritionNo", Long.class);

    public final StringPath processedFoodItemName = createString("processedFoodItemName");

    public final NumberPath<Long> protein = createNumber("protein", Long.class);

    public final NumberPath<Long> userNo = createNumber("userNo", Long.class);

    public QNutrition(String variable) {
        super(Nutrition.class, forVariable(variable));
    }

    public QNutrition(Path<? extends Nutrition> path) {
        super(path.getType(), path.getMetadata());
    }

    public QNutrition(PathMetadata metadata) {
        super(Nutrition.class, metadata);
    }

}

