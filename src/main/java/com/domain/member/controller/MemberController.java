package com.domain.member.controller;

import com.domain.member.dto.Member;
import com.domain.member.service.MemberService;
import com.global.container.Container;
import com.global.rq.Rq;

public class MemberController {
  private MemberService memberService;

  public MemberController() {
    memberService = Container.getMemberService();
  }

  public void doJoin(Rq rq) {
    String username;
    String password;
    String passwordConfirm;
    String name;

    System.out.println("== 회원 가입 ==");

    // 아이디 입력
    while (true) {
      System.out.print("아이디 : ");
      username = Container.getSc().nextLine();

      if(username.trim().isEmpty()) {
        System.out.println("아이디를 입력해주세요.");
        continue;
      }

      Member member = memberService.findByUsername(username);

      if(member != null) {
        System.out.println("이미 존재하는 아이디입니다. 다른 아이디를 입력해주세요.");
        continue;
      }

      break;
    }

    // 비밀번호 입력
    while (true) { 
      System.out.print("비밀번호 : ");
      password = Container.getSc().nextLine();

      if(password.trim().isEmpty()) {
        System.out.println("비밀번호를 입력해주세요.");
        continue;
      }

      while (true) {
        System.out.print("비밀번호 확인 : ");
        passwordConfirm = Container.getSc().nextLine();

        if(passwordConfirm.trim().isEmpty()) {
          System.out.println("비밀번호 확인을 입력해주세요.");
          continue;
        }

        if(!passwordConfirm.equals(password)) {
          System.out.println("비밀번호를 확인 후 다시 입력해주세요.");
          continue;
        }
        break;
      }

      break;
    }

    // 이름 입력
    while (true) {
      System.out.print("이름 : ");
      name = Container.getSc().nextLine();

      if(name.trim().isEmpty()) {
        System.out.println("이름 입력해주세요.");
        continue;
      }

      break;
    }

    Member member = memberService.join(username, password, name);
    System.out.printf("'%s'님 회원 가입되었습니다.\n", member.getName());
  }

  public void doLogin(Rq rq) {
    if(rq.hasAttr("loginedMemberId")) {
      System.out.println("이미 로그인 되어있습니다.");
      return;
    }

    String username;
    String password;
    Member member;

    System.out.println("== 로그인 ==");

    // 아이디 입력
    while (true) {
      System.out.print("아이디 : ");
      username = Container.getSc().nextLine();

      if(username.trim().isEmpty()) {
        System.out.println("아이디를 입력해주세요.");
        continue;
      }

      member = memberService.findByUsername(username);

      if(member == null) {
        System.out.println("존재하지 않는 아이디입니다. 다시 입력해주세요.");
        continue;
      }

      break;
    }

    int passwordTryMaxCount = 3;
    int tryMaxCount = 0;

    // 비밀번호 입력
    while (true) {
      if(passwordTryMaxCount == tryMaxCount) {
        System.out.println("비밀번호 입력 횟수를 초과하였습니다.");
        return;
      }

      System.out.print("비밀번호 : ");
      password = Container.getSc().nextLine();

      if(password.trim().isEmpty()) {
        System.out.println("비밀번호를 입력해주세요.");
        continue;
      }

      if(!member.getPassword().equals(password)) {
        System.out.println("비밀번호를 확인 후 다시 입력해주세요.");
        System.out.printf("비밀번호 틀린 횟수 : %d / %d\n", ++tryMaxCount, passwordTryMaxCount);
        continue;
      }

      break;
    }

    String loginedMemberId = member.getId() + "";
    rq.setAttr("loginedMemberId", loginedMemberId);

    System.out.printf("'%s' 님 환영합니다.\n", member.getName());
  }

  public void doLogout(Rq rq) {
    if(!rq.hasAttr("loginedMemberId")) {
      System.out.println("로그인 되어있지 않습니다.");
      return;
    }

    rq.removeAttr("loginedMemberId");
    System.out.println("로그아웃 되었습니다.");
  }
}
