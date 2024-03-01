package com.healthcare.www.user.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCommunity is a Querydsl query type for Community
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCommunity extends EntityPathBase<Community> {

    private static final long serialVersionUID = 729563863L;

    public static final QCommunity community = new QCommunity("community");

    public final NumberPath<Long> userNo = createNumber("userNo", Long.class);

    public final StringPath writingContent = createString("writingContent");

    public final NumberPath<Long> writingNo = createNumber("writingNo", Long.class);

    public final NumberPath<Long> writingReadCount = createNumber("writingReadCount", Long.class);

    public final DateTimePath<java.time.LocalDateTime> writingRegDate = createDateTime("writingRegDate", java.time.LocalDateTime.class);

    public final StringPath writingTag = createString("writingTag");

    public final StringPath writingTitle = createString("writingTitle");

    public final StringPath writingWriter = createString("writingWriter");

    public QCommunity(String variable) {
        super(Community.class, forVariable(variable));
    }

    public QCommunity(Path<? extends Community> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCommunity(PathMetadata metadata) {
        super(Community.class, metadata);
    }

}

