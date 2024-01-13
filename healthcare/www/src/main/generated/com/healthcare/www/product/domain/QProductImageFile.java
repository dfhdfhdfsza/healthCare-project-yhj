package com.healthcare.www.product.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProductImageFile is a Querydsl query type for ProductImageFile
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProductImageFile extends EntityPathBase<ProductImageFile> {

    private static final long serialVersionUID = 1429227272L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProductImageFile productImageFile = new QProductImageFile("productImageFile");

    public final com.healthcare.www.QBaseTimeEntity _super = new com.healthcare.www.QBaseTimeEntity(this);

    //inherited
    public final DatePath<java.time.LocalDate> createdDate = _super.createdDate;

    //inherited
    public final DatePath<java.time.LocalDate> modifiedDate = _super.modifiedDate;

    public final StringPath productImageName = createString("productImageName");

    public final StringPath productImageSaveDir = createString("productImageSaveDir");

    public final NumberPath<Long> productImageSize = createNumber("productImageSize", Long.class);

    public final QProduct productNo;

    public final StringPath productUUID = createString("productUUID");

    public QProductImageFile(String variable) {
        this(ProductImageFile.class, forVariable(variable), INITS);
    }

    public QProductImageFile(Path<? extends ProductImageFile> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProductImageFile(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProductImageFile(PathMetadata metadata, PathInits inits) {
        this(ProductImageFile.class, metadata, inits);
    }

    public QProductImageFile(Class<? extends ProductImageFile> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.productNo = inits.isInitialized("productNo") ? new QProduct(forProperty("productNo")) : null;
    }

}

