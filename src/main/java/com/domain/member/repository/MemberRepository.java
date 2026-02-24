package com.domain.member.repository;

import com.domain.member.dto.Member;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class MemberRepository {
  private List<Member> members;

  public MemberRepository() {
    members = new ArrayList<>();

    testData();
  }

  void testData() {
    members.add(new Member("user1", "1234", "홍길동"));
    members.add(new Member("user2", "4567", "홍길순"));
    members.add(new Member("user3", "5555", "김길현"));
  }

  public Member save(String username, String password, String name) {
    Member member = new Member(username, password, name);
    members.add(member);

    return member;
  }

  public Member findByUsername(String username) {
    return members.stream()
        .filter(member -> member.getUsername().equals(username))
        .findFirst()
        .orElse(null);
  }
}
