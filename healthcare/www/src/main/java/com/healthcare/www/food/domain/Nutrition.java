package com.healthcare.www.food.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Nutrition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nutritionNo; // pk

    private Long userNo;  // 사용자 번호 (기본 키)

    private String date;  // 날짜 및 시간

    private String eatTime;  // 식사 시간

    private Long carbohydrate;  // 탄수화물

    private Long protein;  // 단백질

    private Long fat;  // 지방

    private Long energyKcal;  // 에너지 (kcal)

    private String processedFoodItemName;  // 가공식품명


}
