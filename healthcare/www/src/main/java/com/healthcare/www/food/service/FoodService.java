package com.healthcare.www.food.service;

import com.healthcare.www.dto.NutritionSummary;
import com.healthcare.www.food.domain.Food;
import com.healthcare.www.food.domain.Nutrition;

import java.io.InputStream;
import java.util.List;

public interface FoodService {

    void readExcelAndSaveToDatabase(String s);


    List<Food> findFoodContaining(String keyword);

    void updateEattingFood(Nutrition nutrition);

    List<NutritionSummary> getTotalEnergyKcalAndDateByUser(long userNo);


    List<Nutrition> getUserEatFoodName(long userNo);
}
