package com.healthcare.www.food.controller;

import com.healthcare.www.food.service.FoodService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/food/")
@RequiredArgsConstructor

public class FoodController {
    private final FoodService fsv;
    private final JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void processExcelData() {
    if(getFoodTableCount()==0) {
        fsv.readExcelAndSaveToDatabase("C:/_healthcare/healthcare/www/src/main/resources/excel/식품의약품안전처_가공식품 품목별 영양성분 DB_20221231.xlsx");
        fsv.readExcelAndSaveToDatabase("C:/_healthcare/healthcare/www/src/main/resources/excel/통합 식품영양성분DB_음식_20240115.xlsx");
    }else {
        log.info("Food 테이블에 값이 이미 존재합니다. 데이터를 읽어오지 않습니다.");
    }
    }

    // 테이블이 존재하는지 확인하는 메서드
    private int getFoodTableCount() {
        String query = "SELECT COUNT(*) FROM food";
        return jdbcTemplate.queryForObject(query, Integer.class);
    }
}
