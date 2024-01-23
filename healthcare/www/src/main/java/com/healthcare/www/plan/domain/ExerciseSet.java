package com.healthcare.www.plan.domain;

import jakarta.persistence.*;
import lombok.*;

import javax.swing.*;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class ExerciseSet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long exerciseSetNo;

    @Column(name = "plan_no")
    private  long planNo;

    @Column(name = "exercise_weight")
    private  int exerciseWeight;

    @Column (name = "exercise_count")
    private  int exerciseCount ;

    @Column (name = "exercise_check")
    private  boolean exerciseCheck ;
}
