package com.healthcare.www.product.repository;

import com.healthcare.www.product.domain.Product;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class QueryRepository {

    private final JPAQueryFactory queryFactory;


}
