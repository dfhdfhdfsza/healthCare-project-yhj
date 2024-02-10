package com.healthcare.www.membership.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Membership {

    @Id @Column(name = "user_id")
    private String userId; // 유저 아이디

    @Column(columnDefinition = "int default 0")
    private Integer point; // 적립 포인트

}
