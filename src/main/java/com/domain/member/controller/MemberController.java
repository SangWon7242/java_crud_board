package com.domain.member.controller;

import com.domain.member.dto.Member;
import com.domain.member.service.MemberService;
import com.global.container.Container;
import com.global.controller.Controller;
import com.global.rq.Rq;
import com.global.util.Util;

public class MemberController implements Controller {
  private MemberService memberService;

  public MemberController() {
    memberService = Container.getMemberService();
  }

  @Override
  public void performAction(Rq rq) {
    if (rq.getActionMethodName().equals("join")) {
      doJoin(rq);
    } else if (rq.getActionMethodName().equals("login")) {
      doLogin(rq);
    } else if (rq.getActionMethodName().equals("logout")) {
      doLogout(rq);
    } else if (rq.getActionMethodName().equals("mypage")) {
      showMyPage(rq);
    } else if (rq.getActionMethodName().equals("findByLoginId")) {
      doFindByLoginId(rq);
    }
  }

  public void doJoin(Rq rq) {
    String username;
    String password;
    String passwordConfirm;
    String name;
    String email;
    Member member;

    System.out.println("== 회원 가입 ==");

    // 아이디 입력
    while (true) {
      System.out.print("아이디 : ");
      username = Container.getSc().nextLine();

      if (username.trim().isEmpty()) {
        System.out.println("아이디를 입력해주세요.");
        continue;
      }

      member = memberService.findByUsername(username);

      if (member != null) {
        System.out.println("이미 존재하는 아이디입니다. 다른 아이디를 입력해주세요.");
        continue;
      }

      break;
    }

    // 비밀번호 입력
    while (true) {
      System.out.print("비밀번호 : ");
      password = Container.getSc().nextLine();

      if (password.trim().isEmpty()) {
        System.out.println("비밀번호를 입력해주세요.");
        continue;
      }

      while (true) {
        System.out.print("비밀번호 확인 : ");
        passwordConfirm = Container.getSc().nextLine();

        if (passwordConfirm.trim().isEmpty()) {
          System.out.println("비밀번호 확인을 입력해주세요.");
          continue;
        }

        if (!passwordConfirm.equals(password)) {
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

      if (name.trim().isEmpty()) {
        System.out.println("이름 입력해주세요.");
        continue;
      }

      break;
    }

    // 이메일 입력
    while (true) {
      System.out.print("이메일 : ");
      email = Container.getSc().nextLine();

      if (email.trim().isEmpty()) {
        System.out.println("이메일 입력해주세요.");
        continue;
      }

      if (!Util.isValidEmail(email)) {
        System.out.println("유효하지 않은 이메일 형식입니다. 다시 입력해주세요.");
        continue;
      }

      member = memberService.findByEmail(email);

      if (member != null) {
        System.out.println("이미 존재하는 이메일입니다. 다른 이메일을 입력해주세요.");
        continue;
      }

      break;
    }

    member = memberService.join(username, password, name, email);

    System.out.printf("'%s'님 회원 가입되었습니다.\n", member.getName());
  }

  public void doLogin(Rq rq) {
    String username;
    String password;
    Member member;

    System.out.println("== 로그인 ==");

    // 아이디 입력
    while (true) {
      System.out.print("아이디 : ");
      username = Container.getSc().nextLine();

      if (username.trim().isEmpty()) {
        System.out.println("아이디를 입력해주세요.");
        continue;
      }

      member = memberService.findByUsername(username);

      if (member == null) {
        System.out.println("존재하지 않는 아이디입니다. 다시 입력해주세요.");
        continue;
      }

      break;
    }

    int passwordTryMaxCount = 3;
    int tryMaxCount = 0;

    // 비밀번호 입력
    while (true) {
      if (passwordTryMaxCount == tryMaxCount) {
        System.out.println("비밀번호 입력 횟수를 초과하였습니다.");
        return;
      }

      System.out.print("비밀번호 : ");
      password = Container.getSc().nextLine();

      if (password.trim().isEmpty()) {
        System.out.println("비밀번호를 입력해주세요.");
        continue;
      }

      if (!member.getPassword().equals(password)) {
        System.out.println("비밀번호를 확인 후 다시 입력해주세요.");
        System.out.printf("비밀번호 틀린 횟수 : %d / %d\n", ++tryMaxCount, passwordTryMaxCount);
        continue;
      }

      break;
    }

    String memberId = member.getId() + "";
    rq.login(memberId);

    System.out.printf("'%s' 님 환영합니다.\n", member.getName());
  }

  public void doLogout(Rq rq) {
    rq.logout();
    System.out.println("로그아웃 되었습니다.");
  }

  private void showMyPage(Rq rq) {
    Member member = rq.getLoginedMember();

    System.out.println("== 내 정보 ==");
    System.out.printf("아이디 : %s\n", member.getUsername());
    System.out.printf("이름 : %s\n", member.getName());
    System.out.printf("이메일 : %s\n", member.getEmail());
  }

  private void doFindByLoginId(Rq rq) {
    String email;
    String name;
    Member member;

    System.out.println("== 아이디 찾기 ==");

    // 이름 입력
    while (true) {
      System.out.print("이름 : ");
      name = Container.getSc().nextLine();

      if (name.trim().isEmpty()) {
        System.out.println("이름 입력해주세요.");
        continue;
      }

      break;
    }

    while (true) {
      System.out.print("회원 가입시 입력한 이메일 : ");
      email = Container.getSc().nextLine();

      if (email.trim().isEmpty()) {
        System.out.println("이메일 입력해주세요.");
        continue;
      }

      if (!Util.isValidEmail(email)) {
        System.out.println("유효하지 않은 이메일 형식입니다. 다시 입력해주세요.");
        continue;
      }

      member = memberService.findByEmail(email);

      if (member == null) {
        System.out.println("존재하지 않는 이메일입니다. 다시 입력해주세요.");
        continue;
      }

      if (!member.getName().equals(name)) {
        System.out.println("입력하신 정보와 일치하는 회원 정보를 찾을 수 없습니다. 다시 확인해 주세요.");
        continue;
      }

      break;
    }

    System.out.printf("가입시 입력 한 아이디 : %s\n", member.getUsername());
  }
}
