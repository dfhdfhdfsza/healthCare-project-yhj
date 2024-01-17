package com.healthcare.www.food.repository;

import com.healthcare.www.food.domain.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FoodRepository extends JpaRepository<Food,Long> {

}
