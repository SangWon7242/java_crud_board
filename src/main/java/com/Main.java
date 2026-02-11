package com;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int lastArticleId = 0;
    Article lastArticle = null;

    System.out.println("== 자바 게시판 시작 ==");

    while (true) {
      System.out.print("명령) ");
      String cmd = sc.nextLine();

      if(cmd.equals("/usr/article/write")) {
        System.out.println("== 게시물 작성 ==");
        System.out.print("제목 : ");
        String title = sc.nextLine();

        System.out.print("내용 : ");
        String content = sc.nextLine();

        int id = ++lastArticleId;

        Article article = new Article(id, title, content);

        lastArticle = article;

        System.out.println("생성 된 게시물 객체 : " + article);
        System.out.printf("%d번 게시물이 작성되었습니다.\n", id);
      } else if(cmd.equals("/usr/article/detail")) {

        Article article = lastArticle;

        if(article == null) {
          System.out.println("게시물이 존재하지 않습니다.");
          continue;
        }

        System.out.printf("== %d번 게시물 상세보기 ==\n", article.id);
        System.out.printf("번호 : %d\n", article.id);
        System.out.printf("제목 : %s\n", article.title);
        System.out.printf("내용 : %s\n", article.content);

      }
      else if(cmd.equals("exit")) {
        break;
      }
      else {
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