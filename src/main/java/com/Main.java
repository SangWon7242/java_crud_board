package com;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("== 자바 게시판 시작 ==");

    while (true) {
      System.out.print("명령) ");
      String cmd = sc.nextLine();

      if(cmd.equals("exit")) {
        break;
      }

      System.out.printf("입력 된 명령어 : %s\n", cmd);
    }

    System.out.println("== 자바 게시판 종료 ==");

    sc.close();
  }
}