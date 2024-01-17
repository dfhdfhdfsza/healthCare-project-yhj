package com.healthcare.www.food.domain;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Food{

    @Id
    private String foodCode;

    // 가공식품 품목명
    private String processedFoodItemName;

    // 식품 대분류명
    private String foodCategory;

    // 식품 소분류명
    private String foodSubCategory;

    // 영양 성분 기준 용량
    private String nutritionalStandardVolume;

    // 에너지 (kcal)
    private double energyKcal;

    // 수분 (g)
    private double moisture;

    // 단백질 (g)
    private double protein;

    // 지방 (g)
    private double fat;

    // 탄수화물 (g)
    private double carbohydrate;

    // 당류 (g)
    private double sugars;

}