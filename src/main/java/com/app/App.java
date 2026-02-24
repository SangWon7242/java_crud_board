package com.app;

import com.domain.member.controller.MemberController;
import com.domain.member.dto.Member;
import com.global.container.Container;
import com.domain.article.article.controller.ArticleController;
import com.global.controller.Controller;
import com.global.rq.Rq;

import java.util.Scanner;

public class App {

  public void run() {
    Scanner sc = new Scanner(System.in);

    System.out.println("== 자바 게시판 시작 ==");

    while (true) {

      Rq rq = new Rq();

      String promptName = "명령";

      Member loginedMember = null;
      if (rq.isLogined()) {
        int memberId = Integer.parseInt(rq.getAttr("loginedMemberId"));
        loginedMember = Container.getMemberService().findById(memberId);
      }

      if (loginedMember != null) {
        promptName = loginedMember.getUsername();
      }

      System.out.printf("%s) ", promptName);
      String cmd = sc.nextLine();

      rq.setCommand(cmd);

      rq.getActionPath();

      Controller controller = getControllerByRequestUri(rq);

      if (controller != null) {
        controller.performAction(rq);
      } else if (cmd.equals("exit")) {
        break;
      } else {
        System.out.println("명령어 확인 후 다시 입력해주세요.");
      }
    }

    System.out.println("== 자바 게시판 종료 ==");

    sc.close();
  }

  private Controller getControllerByRequestUri(Rq rq) {
    switch (rq.getControllerTypeCode()) {
      case "usr":
        switch (rq.getControllerName()) {
          case "member":
            return Container.getMemberController();
          case "article":
            return Container.getArticleController();
        }
        break;
    }

    return null;
  }
}
