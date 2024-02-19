package com.healthcare.www.user.repository;

import com.healthcare.www.user.domain.CommentFavorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CommentFavoriteRepository extends JpaRepository<CommentFavorite, Long> {

  @Transactional
  void deleteByUserNo(long userNo);

  List<CommentFavorite> findByUserNo(long userNo);

  CommentFavorite findByUserNoAndCommentNo(long userNo, long commentNo);
}
