package com.app;

import com.domain.member.controller.MemberController;
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

      System.out.print("명령) ");
      String cmd = sc.nextLine();

      Rq rq = new Rq(cmd);

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
