package com.healthcare.www.food.repository;

import com.healthcare.www.food.domain.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food,Long> {
    @Query("SELECT f FROM Food f WHERE f.processedFoodItemName LIKE %:keyword% ORDER BY f.processedFoodItemName LIMIT 5")
    List<Food> findFoodContaining(@Param("keyword") String keyword);

}


