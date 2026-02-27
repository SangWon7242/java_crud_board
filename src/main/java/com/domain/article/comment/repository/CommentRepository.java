package com.domain.article.comment.repository;

import com.domain.article.comment.dto.Comment;
import com.global.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CommentRepository {
  private List<Comment> comments;

  public CommentRepository() {
    comments = new ArrayList<>();
  }

  public Comment save(int articleId, int memberId, String memberName, String body) {
    String nowDate = Util.getNowDateStr();
    Comment comment = new Comment(nowDate, nowDate, memberId, memberName, articleId, body);
    comments.add(comment);
    return comment;
  }

  public List<Comment> findAllByArticleId(int articleId) {
    return comments.stream()
        .filter(comment -> comment.getArticleId() == articleId)
        .collect(Collectors.toList());
  }

  public Comment findById(int id) {
    return comments.stream()
        .filter(comment -> comment.getId() == id)
        .findFirst()
        .orElse(null);
  }

  public void delete(Comment comment) {
    comments.remove(comment);
  }

  public void modify(int id, String body) {
    Comment comment = findById(id);
    if (comment != null) {
      comment.setBody(body);
      comment.setUpdateDate(Util.getNowDateStr());
    }
  }
}
