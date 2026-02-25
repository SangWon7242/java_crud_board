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
    members.add(new Member("user1", "1234", "홍길동", "user1@test.com"));
    members.add(new Member("user2", "4567", "홍길순", "user2@test.com"));
    members.add(new Member("user3", "5555", "김길현", "user3@test.com"));
  }

  public Member save(String username, String password, String name, String email) {
    Member member = new Member(username, password, name, email);
    members.add(member);

    return member;
  }

  public Member findByUsername(String username) {
    return members.stream()
        .filter(member -> member.getUsername().equals(username))
        .findFirst()
        .orElse(null);
  }

  public Member findById(int id) {
    return members.stream()
        .filter(member -> member.getId() == id)
        .findFirst()
        .orElse(null);
  }

  public Member findByEmail(String email) {
    return members.stream()
        .filter(member -> member.getEmail().equals(email))
        .findFirst()
        .orElse(null);
  }
}
