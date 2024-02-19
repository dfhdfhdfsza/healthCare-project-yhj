package com.healthcare.www.user.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QComment is a Querydsl query type for Comment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QComment extends EntityPathBase<Comment> {

    private static final long serialVersionUID = -146742067L;

    public static final QComment comment = new QComment("comment");

    public final StringPath commentContent = createString("commentContent");

    public final NumberPath<Long> commentFavorite = createNumber("commentFavorite", Long.class);

    public final NumberPath<Long> commentNo = createNumber("commentNo", Long.class);

    public final DateTimePath<java.time.LocalDateTime> commentRegDate = createDateTime("commentRegDate", java.time.LocalDateTime.class);

    public final StringPath commentWriter = createString("commentWriter");

    public final NumberPath<Long> userNo = createNumber("userNo", Long.class);

    public final NumberPath<Long> writingNo = createNumber("writingNo", Long.class);

    public QComment(String variable) {
        super(Comment.class, forVariable(variable));
    }

    public QComment(Path<? extends Comment> path) {
        super(path.getType(), path.getMetadata());
    }

    public QComment(PathMetadata metadata) {
        super(Comment.class, metadata);
    }

}

