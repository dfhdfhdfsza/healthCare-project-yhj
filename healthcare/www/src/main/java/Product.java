import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;

@Entity
@Getter
@Builder
@RequiredArgsConstructor
@ToString
@Embeddable
public class Product {
    // 상품 번호(PK)
    @Id
    private long productNo;
    // 상품명
    @Column(name = "product_name")
    private String productName;
    // 상품 유형
    @Column(name = "product_type")
    @Embedded
    private type productType;
    // 상품 가격
    private int price;
    // 상품 등록일
    @Column(name = "reg_date")
    @CreatedDate
    private LocalDate regDate;
    // 상품 수정일
    @Column(name = "mod_date")
    @LastModifiedDate
    private LocalDate modDate;

    enum type {}



}
