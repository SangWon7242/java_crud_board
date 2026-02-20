package com.app;

import com.global.container.Container;
import com.domain.article.article.controller.ArticleController;
import com.global.rq.Rq;

import java.util.Scanner;

public class App {
  public ArticleController articleController;

  public App() {
    articleController = Container.getArticleController();
  }

  public void run() {
    Scanner sc = new Scanner(System.in);

    System.out.println("== 자바 게시판 시작 ==");

    while (true) {
      System.out.print("명령) ");
      String cmd = sc.nextLine();

      Rq rq = new Rq(cmd);

      if (rq.getActionPath().equals("/usr/article/write")) {
        articleController.doWrite();
      } else if (rq.getActionPath().equals("/usr/article/detail")) {
        articleController.showDetail(rq);
      } else if (rq.getActionPath().equals("/usr/article/list")) {
        articleController.showList(rq);
      } else if (rq.getActionPath().equals("/usr/article/modify")) {
        articleController.doModify(rq);
      } else if (rq.getActionPath().equals("/usr/article/delete")) {
        articleController.doDelete(rq);
      } else if (cmd.equals("exit")) {
        break;
      } else {
        System.out.println("명령어 확인 후 다시 입력해주세요.");
      }
    }

    System.out.println("== 자바 게시판 종료 ==");

    sc.close();
  }
}
