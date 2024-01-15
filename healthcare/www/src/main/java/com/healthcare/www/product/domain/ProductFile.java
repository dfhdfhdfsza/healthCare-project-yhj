package com.healthcare.www.product.domain;

import com.healthcare.www.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Builder
@Table(name = "product_file")
public class ProductFile extends BaseTimeEntity {
    // 파일 고유번호(PK)
    @Id @Column(name = "product_UUID")
    private String productUUID;
    // 상품 번호(FK => 외래키 설정은 DB에서 설정함)
    private long productNo;
    // 파일명
    @Column(name = "product_file_name")
    private String productFileName;
    // 파일 크기
    @Column(name = "product_file_size")
    private long productFileSize;
    // 파일 저장 경로
    @Column(name = "product_file_save_dir")
    private String productFileSaveDir;
    // 파일 유형
    @Column(name = "product_file_type")
    private String productFileType;
}
