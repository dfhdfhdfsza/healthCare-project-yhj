package com.healthcare.www.plan.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class HealthInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long infoNo;

    @Column(name = "name",length = 100)
    private String name;

    @Column(name = "bodypart",length = 100)
    private  String bodypart;

    @Column(name = "equipment",length = 100)
    private  String equipment;

    @Column(name = "target",length = 100)
    private  String target;

    @Column(name = "secondary_muscles",length = 100)
    private  String secondaryMuscles;

    @Column(name = "img_url")
    private  String imgUrl;

    @Column(name = "instructions",columnDefinition ="TEXT")
    private String instructions;

}
