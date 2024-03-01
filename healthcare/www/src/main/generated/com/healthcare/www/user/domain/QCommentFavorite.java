package com.healthcare.www.user.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCommentFavorite is a Querydsl query type for CommentFavorite
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCommentFavorite extends EntityPathBase<CommentFavorite> {

    private static final long serialVersionUID = -1486490231L;

    public static final QCommentFavorite commentFavorite = new QCommentFavorite("commentFavorite");

    public final NumberPath<Long> commentNo = createNumber("commentNo", Long.class);

    public final NumberPath<Long> favoriteNo = createNumber("favoriteNo", Long.class);

    public final NumberPath<Long> userNo = createNumber("userNo", Long.class);

    public QCommentFavorite(String variable) {
        super(CommentFavorite.class, forVariable(variable));
    }

    public QCommentFavorite(Path<? extends CommentFavorite> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCommentFavorite(PathMetadata metadata) {
        super(CommentFavorite.class, metadata);
    }

}

