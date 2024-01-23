package com.healthcare.www.plan.domain;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class ExerciseSetVO {
    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    private  int exerciseSetNo;

    @Column(name = "exercise_weight")
    private  int exerciseWeight;

    @Column (name = "exercise_count")
    private  int exerciseCount ;

    @Column (name = "exercise_check")
    private  boolean exerciseCheck ;
}
