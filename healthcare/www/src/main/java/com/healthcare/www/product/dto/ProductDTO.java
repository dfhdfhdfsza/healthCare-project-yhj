package com.healthcare.www.product.dto;

import com.healthcare.www.product.domain.Product;
import com.healthcare.www.product.domain.ProductFile;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.awt.print.Pageable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO {
    // 상품번호
    private Long productNo;
    // 상품명
    @NotEmpty(message = "상품명을 입력하세요.")
    private String productName;
    // 상품 유형
    @NotEmpty(message = "상품유형을 선택하세요.")
    private String productType;
    // 상품 정보
    @NotEmpty(message = "상품설명을 입력하세요.")
    private String productInfo;
    // 상품 가격
    @NotNull
    private Integer price;
    // 할인율
    private Integer discountRate;
    // 실제 가격
    private Integer realPrice;
    // 첨부파일 리스트
    private List<ProductFileDTO> productFileList;
    // 등록일
    private LocalDateTime regDate;
    // 수정일
    private LocalDateTime modDate;
    // 상품검색용도
    private String category; // 검색유형
    private String keyword; // 검색어

    // Product 엔티티 -> ProductDTO 로 변환 생성자
    public ProductDTO(Product product){
        this.productNo = product.getProductNo();
        this.productName = product.getProductName();
        this.productType = product.getProductType();
        this.productInfo = product.getProductInfo();
        this.price = product.getPrice();
        this.discountRate = product.getDiscountRate();
        this.regDate = product.getRegDate();
        this.modDate = product.getModDate();
    }
    // ProductFileDTO ->
    public ProductDTO(List<ProductFileDTO> productFileDTOList){
        this.productFileList = productFileDTOList;
    }
    public ProductDTO(Product product, List<ProductFileDTO> productFileDTOList){
        this.productNo = product.getProductNo();
        this.productName = product.getProductName();
        this.productType = product.getProductType();
        this.productInfo = product.getProductInfo();
        this.price = product.getPrice();
        this.discountRate = product.getDiscountRate();
        this.regDate = product.getRegDate();
        this.modDate = product.getModDate();
        this.productFileList = productFileDTOList;
    }


    public Integer getRealPrice() {
        return this.price - (int)(this.price * (this.discountRate/100.0));
    }
}
