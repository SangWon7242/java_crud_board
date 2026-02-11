package com;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {
  static void makeArticleTestData(List<Article> articles) {
    /*articles.add(new Article(1, "제목1", "내용1"));
    articles.add(new Article(2, "제목2", "내용2"));
    articles.add(new Article(3, "제목3", "내용3"));*/

    IntStream.rangeClosed(1, 3)
        .forEach(i -> articles.add(new Article(i, "제목" + i, "내용" + i)));
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    List<Article> articles = new ArrayList<>();

    makeArticleTestData(articles);

    int lastArticleId = articles.get(articles.size() - 1).id;

    System.out.println("== 자바 게시판 시작 ==");

    while (true) {
      System.out.print("명령) ");
      String cmd = sc.nextLine();

      if (cmd.equals("/usr/article/write")) {
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
      } else if (cmd.startsWith("/usr/article/detail/")) {
        String[] cmdBits = cmd.split("/");
        System.out.println(Arrays.toString(cmdBits));
        // [, usr, article, detail, 1]

        if (cmdBits.length < 5) {
          System.out.println("명령어를 올바르게 입력해주세요.");
          System.out.println("예) /usr/article/detail/1");
          continue;
        }

        int id = 0;
        try {
          id = Integer.parseInt(cmdBits[cmdBits.length - 1]);
        } catch (NumberFormatException e) {
          System.out.println("게시물 번호는 정수로 입력해주세요.");
          continue;
        }

        // 할일: 이후에는 입력한 id 값과 게시물 객체의 번호와 일치한 게시물 데이터를 가져오기

        Article article = articles.getLast();

        if (article == null) {
          System.out.println("게시물이 존재하지 않습니다.");
          continue;
        }

        System.out.printf("== %d번 게시물 상세보기 ==\n", article.id);
        System.out.printf("번호 : %d\n", article.id);
        System.out.printf("제목 : %s\n", article.title);
        System.out.printf("내용 : %s\n", article.content);

      } else if (cmd.equals("/usr/article/list")) {
        if(articles.isEmpty()) {
          System.out.println("게시물이 존재하지 않습니다.");
          continue;
        }

        System.out.println("== 게시물 리스트 ==");

        System.out.println("번호 | 제목");

        for(int i = articles.size() - 1; i >= 0; i--) {
          Article article = articles.get(i);
          System.out.printf("%d | %s\n", article.id, article.title);
        }

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

class Article {
  int id;
  String title;
  String content;

  Article(int id, String title, String content) {
    this.id = id;
    this.title = title;
    this.content = content;
  }

  @Override
  public String toString() {
    return "{id: %d, title: '%s', content: '%s'}".formatted(id, title, content);
  }
}