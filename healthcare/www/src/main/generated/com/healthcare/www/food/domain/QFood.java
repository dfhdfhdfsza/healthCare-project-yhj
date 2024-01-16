package com.healthcare.www.food.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QFood is a Querydsl query type for Food
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFood extends EntityPathBase<Food> {

    private static final long serialVersionUID = -2053228349L;

    public static final QFood food = new QFood("food");

    public final NumberPath<Double> carbohydrate = createNumber("carbohydrate", Double.class);

    public final NumberPath<Double> energyKcal = createNumber("energyKcal", Double.class);

    public final NumberPath<Double> fat = createNumber("fat", Double.class);

    public final StringPath foodCategory = createString("foodCategory");

    public final StringPath foodCode = createString("foodCode");

    public final StringPath foodSubCategory = createString("foodSubCategory");

    public final NumberPath<Double> moisture = createNumber("moisture", Double.class);

    public final StringPath nutritionalStandardVolume = createString("nutritionalStandardVolume");

    public final StringPath processedFoodItemName = createString("processedFoodItemName");

    public final NumberPath<Double> protein = createNumber("protein", Double.class);

    public final NumberPath<Double> sugars = createNumber("sugars", Double.class);

    public QFood(String variable) {
        super(Food.class, forVariable(variable));
    }

    public QFood(Path<? extends Food> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFood(PathMetadata metadata) {
        super(Food.class, metadata);
    }

}

