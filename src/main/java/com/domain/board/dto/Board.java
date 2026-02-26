package com.domain.board.dto;

import lombok.*;

@AllArgsConstructor // 인스턴스를 인자값으로 받는 생성자 메서드 자동 생성
@Getter
@Setter
@ToString
public class Board {
  private static int lastId;
  private int id;
  private final String name; // 게시판 이름
  private final String code;

  static {
    lastId = 0;
  }

  public Board(String name, String code) {
    this(++lastId, name, code);
  }
}
