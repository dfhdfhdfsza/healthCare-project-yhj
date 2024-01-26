package com.healthcare.www.dto;

import lombok.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
    public class NutritionSummary {
        private String date;
        private Long totalEnergyKcal;
        private Long totalCarbohydrate;
        private Long totalProtein;
        private Long totalFat;
        // 생성자 내용


    }


