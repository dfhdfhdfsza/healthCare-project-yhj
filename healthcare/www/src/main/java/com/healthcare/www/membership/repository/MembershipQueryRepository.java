package com.healthcare.www.membership.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.healthcare.www.membership.domain.QMembership.membership;

@Repository
@RequiredArgsConstructor
public class MembershipQueryRepository {

    private final JPAQueryFactory queryFactory;

    public void updatePoint(String userId, int point) {
        queryFactory.update(membership).
                set(membership.point, membership.point.add(point)).
                where(membership.userId.eq(userId)).
                execute();
    }
}
