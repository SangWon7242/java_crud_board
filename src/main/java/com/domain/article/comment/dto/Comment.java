package com.domain.article.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
  private static int lastId = 0;
  private int id;
  private String createDate;
  private String updateDate;
  private int memberId;
  private String memberName;
  private int articleId;
  private String body;

  public Comment(String createDate, String updateDate, int memberId, String memberName, int articleId, String body) {
    this.id = ++lastId;
    this.createDate = createDate;
    this.updateDate = updateDate;
    this.memberId = memberId;
    this.memberName = memberName;
    this.articleId = articleId;
    this.body = body;
  }
}
