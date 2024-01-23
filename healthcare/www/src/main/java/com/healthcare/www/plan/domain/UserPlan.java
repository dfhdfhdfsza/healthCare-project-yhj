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
public class UserPlan {
    @Id
    private  long userPlanNo;

    @Column(name="plan_date")
    private String planDate;

    @Column(name="user_no")
    private long userNo;

    @Column(name = "plan_no")
    private long planNo;


}
