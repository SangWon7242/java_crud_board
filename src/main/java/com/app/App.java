package com.app;

import com.container.Container;
import com.domain.article.article.Article;
import com.domain.article.article.ArticleController.ArticleController;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class App {
  public ArticleController articleController;

  public App() {
    articleController = Container.articleController;
  }

  public void run() {
    Scanner sc = new Scanner(System.in);

    System.out.println("== 자바 게시판 시작 ==");

    while (true) {
      System.out.print("명령) ");
      String cmd = sc.nextLine();

      if (cmd.equals("/usr/article/write")) {
        articleController.doWrite();
      } else if (cmd.startsWith("/usr/article/detail/")) {
        articleController.showDetail(cmd);
      } else if (cmd.equals("/usr/article/list")) {
        articleController.showList();
      } else if (cmd.startsWith("/usr/article/modify/")) {
        articleController.doModify(cmd);
      } else if (cmd.startsWith("/usr/article/delete/")) {
        articleController.doDelete(cmd);
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
