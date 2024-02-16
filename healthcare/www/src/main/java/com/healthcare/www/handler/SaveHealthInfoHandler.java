package com.healthcare.www.handler;


import com.fasterxml.jackson.databind.JsonNode;
import com.healthcare.www.plan.domain.HealthInfo;
import com.healthcare.www.plan.service.HealthService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
@Slf4j
public class SaveHealthInfoHandler implements ApplicationRunner {
    private final HealthService hsv;

    @PersistenceContext
    private EntityManager entityManager;

    public SaveHealthInfoHandler(HealthService hsv) {
        this.hsv = hsv;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //서버 시작시 실행될 코드
        System.out.println("서버가 시작될 때 실행됩니다.");


        if(getHealthInfoRowCount()!=1324)//health_info테이블에 값이 다 들어있지않으면 실행
        {
            //api 요청
            final String url = "https://exercisedb.p.rapidapi.com/exercises?limit=1324";//max=1324
            final String rapidAPIKey = "9b7b13c7f5mshfd8b94a88797e77p1a4080jsnd904ca818f96";
            final String rapidAPIHost = "exercisedb.p.rapidapi.com";

            HttpHeaders headers = new HttpHeaders();
            headers.set("X-RapidAPI-Key", rapidAPIKey);
            headers.set("X-RapidAPI-Host", rapidAPIHost);
            HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<JsonNode> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, JsonNode.class);


            //값을 제대로 받아왔으면
            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                hsv.resetHealthInfo();  //health_info 테이블 초기화

                JsonNode responseBody = responseEntity.getBody();

                for (JsonNode element : responseBody) {
                    // JSON 배열의 각 요소에 대한 처리
                    // 예: String name = element.get("name").asText();
                    log.info("JsonNode::"+element);
                    //health_info 객체 생성
                    HealthInfo healthInfo=new HealthInfo();
                    healthInfo.setName(element.get("name").asText());
                    healthInfo.setBodypart(element.get("bodyPart").asText());
                    healthInfo.setEquipment(element.get("equipment").asText());
                    healthInfo.setTarget(element.get("target").asText());

                    // secondaryMuscles 배열의 요소들을 문자열로 합치기
                    JsonNode SecondaryMuscles=element.get("secondaryMuscles");
                    StringBuilder secondaryMusclesStringBuilder = new StringBuilder();
                    for (JsonNode muscleNode : SecondaryMuscles) {
                        if (!secondaryMusclesStringBuilder.isEmpty()) {
                            secondaryMusclesStringBuilder.append(", ");
                        }
                        secondaryMusclesStringBuilder.append(muscleNode.asText());
                    }
                    // 문자열로 합쳐진 secondaryMuscles 값을 저장
                    healthInfo.setSecondaryMuscles(secondaryMusclesStringBuilder.toString());

                    healthInfo.setImgUrl(element.get("gifUrl").asText());


                    //instructions배열의 요소들을 문자열로 합치기
                    JsonNode instructions=element.get("instructions");
                    StringBuilder instructionsStringBuilder = new StringBuilder();
                    for (JsonNode instructionNode : instructions) {
                        if (!instructionsStringBuilder.isEmpty()) {
                            instructionsStringBuilder.append(", ");
                        }
                        instructionsStringBuilder.append(instructionNode.asText());
                    }
                    healthInfo.setInstructions(instructionsStringBuilder.toString());

                    hsv.saveHealthInfo(healthInfo);
                }
            }
        }
        else{
            log.info("이미 값이 있습니다.");
        }

    }

    //health_info테이블의 row수를 반환하는 메서드
    public long getHealthInfoRowCount() {
        String sql = "SELECT COUNT(*) FROM HealthInfo"; // 엔티티명인 HealthInfo를 사용합니다.
        Query query = entityManager.createQuery(sql);
        return (long) query.getSingleResult();
    }
}