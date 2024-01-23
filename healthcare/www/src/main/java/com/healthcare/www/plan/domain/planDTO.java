package com.healthcare.www.plan.domain;

import lombok.*;

import java.util.List;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class planDTO {
    private long userNo;
    private String planDate ;
    private String exerciseName;
    private List<ExerciseSet> setVOList;
}
