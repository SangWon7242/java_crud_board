package com.domain.member.service;

import com.domain.member.dto.Member;
import com.domain.member.repository.MemberRepository;
import com.global.container.Container;

public class MemberService {
  private MemberRepository memberRepository;

  public MemberService() {
    memberRepository = Container.getMemberRepository();
  }

  public Member join(String username, String password, String name) {
    return memberRepository.save(username, password, name);
  }
}
