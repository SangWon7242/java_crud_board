package com.domain.member.service;

import com.domain.member.dto.Member;
import com.domain.member.repository.MemberRepository;
import com.global.container.Container;

public class MemberService {
  private MemberRepository memberRepository;

  public MemberService() {
    memberRepository = Container.getMemberRepository();
  }

  public Member join(String username, String password, String name, String email) {
    return memberRepository.save(username, password, name, email);
  }

  public Member findByUsername(String username) {
    return memberRepository.findByUsername(username);
  }

  public Member findById(int id) {
    return memberRepository.findById(id);
  }

  public Member findByEmail(String email) {
    return memberRepository.findByEmail(email);
  }

  public void modifiedPassword(String email, String password) {
    memberRepository.modifiedPassword(email, password);
  }
}
