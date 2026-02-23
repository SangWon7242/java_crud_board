package com.domain.member.repository;

import com.domain.member.dto.Member;

import java.util.ArrayList;
import java.util.List;

public class MemberRepository {
  private List<Member> members;

  public MemberRepository() {
    members = new ArrayList<>();
  }

  public Member save(String username, String password, String name) {
    Member member = new Member(username, password, name);
    members.add(member);

    return member;
  }
}
