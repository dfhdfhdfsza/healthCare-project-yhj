package com.healthcare.www.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FoodDTO {
    private String foodCode;
    private String processedFoodItemName;
    private String foodCategory;
    private String foodSubCategory;
    private String nutritionalStandardVolume;
    private double energyKcal;
    private double moisture;
    private double protein;
    private double fat;
    private double carbohydrate;
    private double sugars;
}
