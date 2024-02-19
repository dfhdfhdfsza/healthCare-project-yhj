package com.healthcare.www.user.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCommunityFile is a Querydsl query type for CommunityFile
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCommunityFile extends EntityPathBase<CommunityFile> {

    private static final long serialVersionUID = -1149081357L;

    public static final QCommunityFile communityFile = new QCommunityFile("communityFile");

    public final NumberPath<Long> userNo = createNumber("userNo", Long.class);

    public final StringPath writingFileName = createString("writingFileName");

    public final StringPath writingFileSaveDir = createString("writingFileSaveDir");

    public final NumberPath<Long> writingFileSize = createNumber("writingFileSize", Long.class);

    public final StringPath writingFileType = createString("writingFileType");

    public final NumberPath<Long> writingNo = createNumber("writingNo", Long.class);

    public final StringPath writingUUID = createString("writingUUID");

    public QCommunityFile(String variable) {
        super(CommunityFile.class, forVariable(variable));
    }

    public QCommunityFile(Path<? extends CommunityFile> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCommunityFile(PathMetadata metadata) {
        super(CommunityFile.class, metadata);
    }

}

