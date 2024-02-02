package com.healthcare.www.product.repository;

import com.healthcare.www.product.dto.ProductDTO;
import com.healthcare.www.product.dto.ProductFileDTO;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.healthcare.www.product.domain.QProduct.product;
import static com.healthcare.www.product.domain.QProductFile.productFile;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ProductQueryRepository {

    private final JPAQueryFactory queryFactory;
    // 상품 목록 페이징 조건으로 검색, 조건에 맞는 상품목록 개수 조회
    public Page<ProductDTO> getProductListAndPaging(ProductDTO productDTO, Pageable pageable) {
        // DTO 객체로 맵핑하기 위해 Projection 객체로 바인딩 bean => setter 로 맵핑 (그 외 fields, constructor, Constructor + @QueryProjection 가능)
        List<ProductDTO> productDTOList = queryFactory
                .select(Projections.bean(ProductDTO.class,
                        product.productNo,
                        product.productName,
                        product.productInfo,
                        product.productType,
                        product.price,
                        product.discountRate,
                        product.regDate,
                        product.modDate))
                .from(product)
                .where(productNoEp(productDTO.getProductNo()), // productNo가 null 이면 제외
                        productNameEp(productDTO.getProductName()), // productName가 null 이면 제외
                        productTypeEq(productDTO.getProductType()), // productType이 null 이면 제외
                        productInfoEq(productDTO.getProductInfo()) // productInfo가 null 이면 제외
                )
                .orderBy()
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        for (ProductDTO p : productDTOList) {
            List<ProductFileDTO> productFileDTOList = queryFactory
                    .select(Projections.bean(ProductFileDTO.class,
                            productFile.productFileSaveDir,
                            productFile.productFileName,
                            productFile.productNo))
                    .from(productFile)
                    .join(product).on(productFile.productNo.eq(product.productNo))
                    .where(productNoEp(p.getProductNo()))
                    .fetch();
            p.setProductFileList(productFileDTOList);
        }
        JPAQuery<Long> countQuery = queryFactory // 조건에 맞는 상품수 구하기
                //.select(Wildcard.count) // select count(*)
                .select(Wildcard.count)
                .from(product)
                .where(productTypeEq(productDTO.getProductType()));

        return PageableExecutionUtils.getPage(productDTOList, pageable, countQuery::fetchOne);
    }


    // 값이 null 인지 체크하는 BooleanExpression 함수

    private BooleanExpression productNoEp(Long productNo){
        return productNo != null ? product.productNo.eq(productNo) : null;
    }
    private BooleanExpression productNameEp(String productName) {
        return productName != null ? product.productName.eq(productName) : null;
    }
    private BooleanExpression productInfoEq(String productInfo) {
        return productInfo != null ? product.productInfo.eq(productInfo) : null;
    }
    private BooleanExpression productTypeEq(String productType) {
        return productType != null ? product.productType.eq(productType) : null;
    }


}
