package com.app;

import com.domain.article.article.Article;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class App {
  void makeArticleTestData(List<Article> articles) {
    IntStream.rangeClosed(1, 3)
        .forEach(i -> articles.add(new Article(i, "제목" + i, "내용" + i)));
  }

  public void run() {
    Scanner sc = new Scanner(System.in);
    List<Article> articles = new ArrayList<>();

    makeArticleTestData(articles);

    int lastArticleId = articles.get(articles.size() - 1).id;

    System.out.println("== 자바 게시판 시작 ==");

    while (true) {
      System.out.print("명령) ");
      String cmd = sc.nextLine();

      if (cmd.equals("/usr/article/write")) {
        actionUsrArticleWrite(sc, lastArticleId, articles);
        lastArticleId++;
      } else if (cmd.startsWith("/usr/article/detail/")) {
        actionUsrArticleDetail(cmd, articles);
      } else if (cmd.equals("/usr/article/list")) {
        actionUsrArticleList(articles);
      } else if (cmd.startsWith("/usr/article/modify/")) {
        actionUsrArticleModify(cmd, sc, articles);
      } else if (cmd.startsWith("/usr/article/delete/")) {
        actionUsrArticleDelete(cmd, articles);
      } else if (cmd.equals("exit")) {
        break;
      } else {
        System.out.println("명령어 확인 후 다시 입력해주세요.");
      }
    }

    System.out.println("== 자바 게시판 종료 ==");

    sc.close();
  }

  void actionUsrArticleList(List<Article> articles) {
    if (articles.isEmpty()) {
      System.out.println("게시물이 존재하지 않습니다.");
      return;
    }

    System.out.println("== 게시물 리스트 ==");

    System.out.println("번호 | 제목");

    for (int i = articles.size() - 1; i >= 0; i--) {
      Article article = articles.get(i);
      System.out.printf("%d | %s\n", article.id, article.title);
    }
  }

  void actionUsrArticleDelete(String cmd, List<Article> articles) {
    String[] cmdBits = cmd.split("/");

    if (cmdBits.length < 5) {
      System.out.println("명령어를 올바르게 입력해주세요.");
      System.out.println("예) /usr/article/detail/1");
      return;
    }

    int id = 0;
    try {
      id = Integer.parseInt(cmdBits[cmdBits.length - 1]);
    } catch (NumberFormatException e) {
      System.out.println("게시물 번호는 정수로 입력해주세요.");
      return;
    }

    int finalId = id;
    Article foundArticle = articles.stream()
        .filter(article -> article.id == finalId)
        .findFirst() // 찾은 것중에 첫 번째 데이터 가져와라
        .orElse(null); // 못 찾은 경우에는 null을 넣어라

    if (foundArticle == null) {
      System.out.printf("%d번 게시물이 존재하지 않습니다.\n", id);
      return;
    }

    articles.remove(foundArticle);
    System.out.printf("%d번 게시물이 삭제되었습니다.\n", id);

  }

  void actionUsrArticleModify(String cmd, Scanner sc, List<Article> articles) {
    String[] cmdBits = cmd.split("/");

    if (cmdBits.length < 5) {
      System.out.println("명령어를 올바르게 입력해주세요.");
      System.out.println("예) /usr/article/detail/1");
      return;
    }

    int id = 0;
    try {
      id = Integer.parseInt(cmdBits[cmdBits.length - 1]);
    } catch (NumberFormatException e) {
      System.out.println("게시물 번호는 정수로 입력해주세요.");
      return;
    }

    int finalId = id;
    Article foundArticle = articles.stream()
        .filter(article -> article.id == finalId)
        .findFirst() // 찾은 것중에 첫 번째 데이터 가져와라
        .orElse(null); // 못 찾은 경우에는 null을 넣어라

    if (foundArticle == null) {
      System.out.printf("%d번 게시물이 존재하지 않습니다.\n", id);
      return;
    }

    System.out.printf("== %d번 게시물 수정 ==\n", foundArticle.id);
    System.out.print("수정 할 제목 : ");
    String title = sc.nextLine();

    System.out.print("수정 할 내용 : ");
    String content = sc.nextLine();

    foundArticle.title = title;
    foundArticle.content = content;
    System.out.printf("%d번 게시물이 수정되었습니다.\n", id);
  }

  void actionUsrArticleDetail(String cmd, List<Article> articles) {
    String[] cmdBits = cmd.split("/");

    if (cmdBits.length < 5) {
      System.out.println("명령어를 올바르게 입력해주세요.");
      System.out.println("예) /usr/article/detail/1");
      return; // return을 만나면 함수는 그 즉시 종료, continue 비슷한 기능을 수행
    }

    int id = 0;
    try {
      id = Integer.parseInt(cmdBits[cmdBits.length - 1]);
    } catch (NumberFormatException e) {
      System.out.println("게시물 번호는 정수로 입력해주세요.");
      return;
    }

    int finalId = id;
    Article foundArticle = articles.stream()
        .filter(article -> article.id == finalId)
        .findFirst() // 찾은 것중에 첫 번째 데이터 가져와라
        .orElse(null); // 못 찾은 경우에는 null을 넣어라

    if (foundArticle == null) {
      System.out.printf("%d번 게시물이 존재하지 않습니다.\n", id);
      return;
    }

    System.out.printf("== %d번 게시물 상세보기 ==\n", foundArticle.id);
    System.out.printf("번호 : %d\n", foundArticle.id);
    System.out.printf("제목 : %s\n", foundArticle.title);
    System.out.printf("내용 : %s\n", foundArticle.content);
  }

  void actionUsrArticleWrite(Scanner sc, int lastArticleId, List<Article> articles) {
    System.out.println("== 게시물 작성 ==");
    System.out.print("제목 : ");
    String title = sc.nextLine();

    System.out.print("내용 : ");
    String content = sc.nextLine();

    int id = ++lastArticleId;

    Article article = new Article(id, title, content);

    articles.add(article);

    System.out.println("생성 된 게시물 객체 : " + article);
    System.out.printf("%d번 게시물이 작성되었습니다.\n", id);
  }
}
