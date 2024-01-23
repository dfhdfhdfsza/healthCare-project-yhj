package com.healthcare.www.plan.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class PlanCalendar {

    @Id
    private long planNo;

    @Column(name = "exercise_name")
    private String exerciseName;


//    @Column
//    private  String plan_memo;
}
