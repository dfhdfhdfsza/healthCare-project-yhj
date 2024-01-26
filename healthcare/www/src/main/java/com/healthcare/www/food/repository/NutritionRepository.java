package com.healthcare.www.food.repository;

import com.healthcare.www.dto.NutritionSummary;
import com.healthcare.www.food.domain.Nutrition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NutritionRepository extends JpaRepository<Nutrition,Long> {
    @Query("SELECT NEW com.healthcare.www.dto.NutritionSummary(n.date, SUM(n.energyKcal) " +
            ",SUM(n.carbohydrate) ,SUM(n.protein), SUM(n.fat)) FROM Nutrition n WHERE n.userNo = :userNo GROUP BY n.date")
    List<NutritionSummary> getTotalEnergyKcalAndDateByUser(@Param("userNo") long userNo);

}
