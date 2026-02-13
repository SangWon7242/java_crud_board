package com.domain.article.article.ArticleController;

import com.container.Container;
import com.domain.article.article.Article;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ArticleController {
  public List<Article> articles;
  public int lastArticleId;

  public ArticleController() {
    articles = new ArrayList<>();

    makeArticleTestData();

    lastArticleId = articles.get(articles.size() - 1).id;
  }

  void makeArticleTestData() {
    IntStream.rangeClosed(1, 3)
        .forEach(i -> articles.add(new Article(i, "제목" + i, "내용" + i)));
  }

  public void doWrite() {
    System.out.println("== 게시물 작성 ==");
    System.out.print("제목 : ");
    String title = Container.sc.nextLine();

    System.out.print("내용 : ");
    String content = Container.sc.nextLine();

    int id = ++lastArticleId;

    Article article = new Article(id, title, content);

    articles.add(article);

    System.out.println("생성 된 게시물 객체 : " + article);
    System.out.printf("%d번 게시물이 작성되었습니다.\n", id);
  }

  public void showDetail(String cmd) {
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

    Article article = findById(id, articles);

    if (article == null) {
      System.out.printf("%d번 게시물이 존재하지 않습니다.\n", id);
      return;
    }

    System.out.printf("== %d번 게시물 상세보기 ==\n", article.id);
    System.out.printf("번호 : %d\n", article.id);
    System.out.printf("제목 : %s\n", article.title);
    System.out.printf("내용 : %s\n", article.content);
  }

  public void showList() {
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

  public void doModify(String cmd) {
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

    Article article = findById(id, articles);

    if (article == null) {
      System.out.printf("%d번 게시물이 존재하지 않습니다.\n", id);
      return;
    }

    System.out.printf("== %d번 게시물 수정 ==\n", article.id);
    System.out.print("수정 할 제목 : ");
    String title = Container.sc.nextLine();

    System.out.print("수정 할 내용 : ");
    String content = Container.sc.nextLine();

    article.title = title;
    article.content = content;
    System.out.printf("%d번 게시물이 수정되었습니다.\n", id);
  }

  public void doDelete(String cmd) {
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

    Article article = findById(id, articles);

    if (article == null) {
      System.out.printf("%d번 게시물이 존재하지 않습니다.\n", id);
      return;
    }

    articles.remove(article);
    System.out.printf("%d번 게시물이 삭제되었습니다.\n", id);
  }

  // id를 기반으로 게시물을 찾는 메서드
  Article findById(int id, List<Article> articles) {
    return articles.stream()
        .filter(article -> article.id == id)
        .findFirst() // 찾은 것중에 첫 번째 데이터 가져와라
        .orElse(null); // 못 찾은 경우에는 null을 넣어라
  }
}
