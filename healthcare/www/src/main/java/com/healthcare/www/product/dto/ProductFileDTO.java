package com.healthcare.www.product.dto;

import com.healthcare.www.product.domain.ProductFile;
import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Builder
public class ProductFileDTO {

    private String productUUID;

    private Long productNo;

    private String productFileName;

    private long productFileSize;

    private String productFileSaveDir;

    private String productFileType;

    // ProductFile 엔티티 -> ProductFileDTO로 변환 생성자
    @QueryProjection
    public ProductFileDTO(ProductFile productFile){
        this.productUUID = productFile.getProductUUID();
        this.productFileName = productFile.getProductFileName();
        this.productFileType = productFile.getProductFileType();
        this.productFileSize = productFile.getProductFileSize();
        this.productNo = productFile.getProductNo();
        this.productFileSaveDir = productFile.getProductFileSaveDir();
    }
}
