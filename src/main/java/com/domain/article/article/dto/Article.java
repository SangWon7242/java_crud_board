package com.domain.article.article.dto;

import lombok.*;

@AllArgsConstructor // 인스턴스를 인자값으로 받는 생성자 메서드 자동 생성
@NoArgsConstructor // 인자값을 받지 않는 생성자 메서드 자동생성
@Getter
@Setter
@ToString
public class Article {
  private static int lastId;
  private int id;
  private String title;
  private String content;
  private int memberId;
  private String writerName;

  static {
    lastId = 0;
  }

  public Article(String title, String content, int memberId, String writerName) {
    this(++lastId, title, content, memberId, writerName);
  }
}
