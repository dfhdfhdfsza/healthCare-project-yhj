package com.healthcare.www.product.repository;

import com.healthcare.www.product.domain.Product;
import com.healthcare.www.product.domain.QProduct;
import com.healthcare.www.product.dto.ProductDTO;
import com.healthcare.www.product.dto.ProductFileDTO;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

import static com.healthcare.www.product.domain.QProduct.product;

@Repository
@RequiredArgsConstructor
public class ProductQueryRepository {

    private final JPAQueryFactory queryFactory;


}
