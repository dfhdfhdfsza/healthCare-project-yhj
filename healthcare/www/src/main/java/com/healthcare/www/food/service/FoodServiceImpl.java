package com.healthcare.www.food.service;

import com.healthcare.www.dto.FoodDTO;
import com.healthcare.www.dto.NutritionSummary;
import com.healthcare.www.food.domain.Food;
import com.healthcare.www.food.domain.Nutrition;
import com.healthcare.www.food.repository.FoodRepository;
import com.healthcare.www.food.repository.NutritionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
@RequiredArgsConstructor
@Service
public class FoodServiceImpl implements FoodService {

    private final FoodRepository foodRepository;
    private final NutritionRepository nutritionRepository;


    @Override
    @Transactional

    public void readExcelAndSaveToDatabase(String filePath) {
        try (FileInputStream fileInputStream = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fileInputStream)) {

            Sheet sheet = workbook.getSheetAt(0); // 첫 번째 시트를 가져옴

            for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                Row row = sheet.getRow(rowIndex);

                if (row != null) {
                    // FoodDTO를 생성하고 엑셀의 각 열 값을 설정
                    FoodDTO foodDTO = new FoodDTO();
                    foodDTO.setFoodCode(getStringCellValue(row.getCell(0)));
                    foodDTO.setProcessedFoodItemName(getStringCellValue(row.getCell(1)));
                    foodDTO.setFoodCategory(getStringCellValue(row.getCell(2)));
                    foodDTO.setFoodSubCategory(getStringCellValue(row.getCell(3)));
                    foodDTO.setNutritionalStandardVolume(getStringCellValue(row.getCell(4)));
                    foodDTO.setEnergyKcal(getNumericCellValue(row.getCell(5)));
                    foodDTO.setMoisture(getNumericCellValue(row.getCell(6)));
                    foodDTO.setProtein(getNumericCellValue(row.getCell(7)));
                    foodDTO.setFat(getNumericCellValue(row.getCell(8)));
                    foodDTO.setCarbohydrate(getNumericCellValue(row.getCell(9)));
                    foodDTO.setSugars(getNumericCellValue(row.getCell(10)));



                    // FoodDTO를 Food 엔티티로 변환하고 데이터베이스에 저장
                    Food food = new Food();
                    BeanUtils.copyProperties(foodDTO, food);
                    foodRepository.save(food);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            // 예외 처리 로직 추가
        }
    }

    @Override
    @Transactional
    public List<Food> findFoodContaining(String keyword) {
        List<Food> foodList = foodRepository.findFoodContaining(keyword);
        return foodList;
    }

    @Override
    public void updateEattingFood(Nutrition nutrition) {
        nutritionRepository.save(nutrition);
    }

    @Override
    public List<NutritionSummary> getTotalEnergyKcalAndDateByUser(long userNo) {
       return nutritionRepository.getTotalEnergyKcalAndDateByUser(userNo);

    }

    @Override
    public List<Nutrition> getUserEatFoodName(long userNo) {
        return  nutritionRepository.getUserEatFoodName(userNo);
    }

    private double getNumericCellValue(Cell cell) {
        if (cell != null) {
            if (cell.getCellType() == CellType.NUMERIC) {
                double value = Math.round(cell.getNumericCellValue() *10 / 10.0);
                return value;
            } else {

                String stringValue = getStringCellValue(cell);
                try {
                    double numericValue =  Math.round(Double.parseDouble(stringValue)*10 / 10.0);
                    return numericValue;
                } catch (NumberFormatException e) {
                    return 0.0;
                }
            }
        } else {
            // cell이 null인 경우에 대한 처리 로직 추가
            System.out.println("DEBUG: Cell is null");
            return 0.0;
        }
    }

    private String getStringCellValue(Cell cell) {
        if (cell == null) {
            return null;
        }

        if (cell.getCellType() == CellType.NUMERIC) {
            // 숫자인 경우 DataFormatter를 사용하여 문자열로 변환
            DataFormatter dataFormatter = new DataFormatter();
            return dataFormatter.formatCellValue(cell);
        }

        return cell.getStringCellValue();
    }
}
