package com.healthcare.www.product.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QProductFile is a Querydsl query type for ProductFile
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProductFile extends EntityPathBase<ProductFile> {

    private static final long serialVersionUID = -354278069L;

    public static final QProductFile productFile = new QProductFile("productFile");

    public final com.healthcare.www.QBaseTimeEntity _super = new com.healthcare.www.QBaseTimeEntity(this);

    //inherited
    public final DatePath<java.time.LocalDate> createdDate = _super.createdDate;

    //inherited
    public final DatePath<java.time.LocalDate> modifiedDate = _super.modifiedDate;

    public final StringPath productFileName = createString("productFileName");

    public final StringPath productFileSaveDir = createString("productFileSaveDir");

    public final NumberPath<Long> productFileSize = createNumber("productFileSize", Long.class);

    public final StringPath productFileType = createString("productFileType");

    public final NumberPath<Long> productNo = createNumber("productNo", Long.class);

    public final StringPath productUUID = createString("productUUID");

    public QProductFile(String variable) {
        super(ProductFile.class, forVariable(variable));
    }

    public QProductFile(Path<? extends ProductFile> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProductFile(PathMetadata metadata) {
        super(ProductFile.class, metadata);
    }

}

