package com.healthcare.www.user.repository;

import com.healthcare.www.user.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {


  List<Comment> findByWritingNo(long writingNo);


  Comment findByCommentNo(long commentNo);

  @Transactional
  void deleteByUserNo(long userNo);
}
