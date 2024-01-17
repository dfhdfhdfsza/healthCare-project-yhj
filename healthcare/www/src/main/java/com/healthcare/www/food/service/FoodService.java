package com.healthcare.www.food.service;

import com.healthcare.www.food.domain.Food;

import java.io.InputStream;
import java.util.List;

public interface FoodService {

    void readExcelAndSaveToDatabase(String s);


    List<Food> findFoodContaining(String keyword);
}
