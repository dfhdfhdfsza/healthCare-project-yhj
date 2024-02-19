package com.healthcare.www.user.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserFile is a Querydsl query type for UserFile
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserFile extends EntityPathBase<UserFile> {

    private static final long serialVersionUID = 81481529L;

    public static final QUserFile userFile = new QUserFile("userFile");

    public final StringPath userFileName = createString("userFileName");

    public final StringPath userFileSaveDir = createString("userFileSaveDir");

    public final NumberPath<Long> userFileSize = createNumber("userFileSize", Long.class);

    public final StringPath userFileType = createString("userFileType");

    public final NumberPath<Long> userNo = createNumber("userNo", Long.class);

    public final StringPath userUUID = createString("userUUID");

    public QUserFile(String variable) {
        super(UserFile.class, forVariable(variable));
    }

    public QUserFile(Path<? extends UserFile> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserFile(PathMetadata metadata) {
        super(UserFile.class, metadata);
    }

}

