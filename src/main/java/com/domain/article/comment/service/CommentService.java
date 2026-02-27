package com.domain.article.comment.service;

import com.domain.article.comment.dto.Comment;
import com.domain.article.comment.repository.CommentRepository;
import com.global.container.Container;

import java.util.List;

public class CommentService {
  private CommentRepository commentRepository;

  public CommentService() {
    this.commentRepository = Container.getCommentRepository();
  }

  public Comment write(int articleId, int memberId, String memberName, String body) {
    return commentRepository.save(articleId, memberId, memberName, body);
  }

  public List<Comment> findAllByArticleId(int articleId) {
    return commentRepository.findAllByArticleId(articleId);
  }

  public Comment findById(int id) {
    return commentRepository.findById(id);
  }

  public void delete(Comment comment) {
    commentRepository.delete(comment);
  }

  public void modify(int id, String body) {
    commentRepository.modify(id, body);
  }
}
