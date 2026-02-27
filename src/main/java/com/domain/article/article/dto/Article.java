package com.domain.article.article.dto;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor // 인스턴스를 인자값으로 받는 생성자 메서드 자동 생성
@NoArgsConstructor // 인자값을 받지 않는 생성자 메서드 자동생성
@Getter
@Setter
@ToString
public class Article {
  private static int lastId;
  private int id;
  private String createDate; // 생성날짜
  private String updateDate; // 수정날짜
  private String title;
  private String content;
  private int memberId;
  private String writerName;
  private int boardId;
  private String boardName;
  private int hit;

  static {
    lastId = 0;
  }

  public Article(String createDate, String updateDate,String title, String content, int memberId, String writerName, int boardId, String boardName, int hit) {
    this(++lastId, createDate, updateDate, title, content, memberId, writerName, boardId, boardName, hit);
  }
}
