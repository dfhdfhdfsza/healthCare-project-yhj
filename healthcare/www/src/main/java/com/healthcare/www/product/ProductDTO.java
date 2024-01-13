package com.healthcare.www.product;

import com.healthcare.www.product.domain.Product;
import com.healthcare.www.product.domain.ProductImageFile;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private Product product;
    private List<ProductImageFile> productImageFileList;

}
