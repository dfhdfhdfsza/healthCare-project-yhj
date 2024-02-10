package com.healthcare.www.membership.dto;

import com.healthcare.www.membership.domain.Membership;
import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MembershipDTO{

    private String userId; // 유저 아이디

    private Integer point; // 적립금



    @QueryProjection
    public MembershipDTO(Membership membership){
        this.userId = membership.getUserId();
        this.point = membership.getPoint();
    }
}
