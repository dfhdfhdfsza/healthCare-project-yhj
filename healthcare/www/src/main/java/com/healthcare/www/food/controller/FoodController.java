package com.healthcare.www.food.controller;

import com.healthcare.www.food.domain.Food;
import com.healthcare.www.food.service.FoodService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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
        fsv.readExcelAndSaveToDatabase("C:/_healthcare/healthcare/www/src/main/resources/excel/통합 식품영양성분DB_음식_20240115.xlsx");
        fsv.readExcelAndSaveToDatabase("C:/_healthcare/healthcare/www/src/main/resources/excel/식품의약품안전처_가공식품 품목별 영양성분 DB_20221231.xlsx");
          }else {
        log.info("Food 테이블에 값이 이미 존재합니다. 데이터를 읽어오지 않습니다.");
    }
    }
    //영양페이지 이동
    @GetMapping("/nutrition")
    private void getNutrition(){}

    @PostMapping("/checkFood")
    private String checkFood(){
         return "redirect:/nutrition";
    }
    //음식 검색
    @GetMapping(value="/findFood", produces = {MediaType.APPLICATION_JSON_VALUE} )
    public ResponseEntity<List<Food>> findFood(@RequestParam String keyword) {
        List<Food> list  =  fsv.findFoodContaining(keyword);
        int isOk=0;
        if(list != null && !list.isEmpty()) {
            isOk=1;
        }
        return isOk > 0 ? new ResponseEntity<List<Food>>(list, HttpStatus.OK)
                : new ResponseEntity<List<Food>>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // 테이블이 존재하는지 확인하는 메서드
    private int getFoodTableCount() {
        String query = "SELECT COUNT(*) FROM food";
        return jdbcTemplate.queryForObject(query, Integer.class);
    }
}
