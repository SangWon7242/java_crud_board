package com;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
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

        int id = 1;
        System.out.printf("%d번 게시물이 작성되었습니다.\n", id);
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