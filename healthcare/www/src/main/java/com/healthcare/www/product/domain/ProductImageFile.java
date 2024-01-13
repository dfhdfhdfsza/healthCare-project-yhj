package com.healthcare.www.product.domain;

import com.healthcare.www.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Builder
@Table(name = "product_image_file")
public class ProductImageFile extends BaseTimeEntity {
    @Id @Column(name = "product_UUID")
    private String productUUID;

    @JoinColumn(name = "product_no")
    @ManyToOne(targetEntity = Product.class)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private long productNo;

    @Column(name = "product_image_name")
    private String productImageName;

    @Column(name = "product_image_size")
    private long productImageSize;

    @Column(name = "product_save_dir")
    private String productImageSaveDir;

}
