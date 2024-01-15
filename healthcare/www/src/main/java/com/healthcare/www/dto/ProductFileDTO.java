package com.healthcare.www.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Builder
public class ProductFileDTO {

    private String productUUID;

    private String productFileName;

    private long productFileSize;

    private String productFileSaveDir;

    private String productFileType;
}
