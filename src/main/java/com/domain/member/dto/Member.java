package com.domain.member.dto;

import lombok.*;

@AllArgsConstructor // 인스턴스를 인자값으로 받는 생성자 메서드 자동 생성
@NoArgsConstructor // 인자값을 받지 않는 생성자 메서드 자동생성
@Getter
@Setter
@ToString
public class Member {
  private static int lastId;
  private int id;
  private String username;
  private String password;
  private String name;
  private String email;

  static {
    lastId = 0;
  }

  public String getType() {
    return isAdmin() ? "관리자" : "일반회원";
  }

  public boolean isAdmin() {
    return username.equals("admin");
  }

  public Member(String username, String password, String name, String email) {
    this(++lastId, username, password, name, email);
  }
}
