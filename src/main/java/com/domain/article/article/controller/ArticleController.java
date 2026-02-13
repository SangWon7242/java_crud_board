package com.domain.article.article.controller;

import com.global.container.Container;
import com.domain.article.article.Article;
import com.domain.article.article.service.ArticleService;
import com.global.rq.Rq;

import java.util.List;

public class ArticleController {
  private ArticleService articleService;

  public ArticleController() {
    articleService = Container.articleService;
  }

  public void doWrite() {
    System.out.println("== 게시물 작성 ==");
    System.out.print("제목 : ");
    String title = Container.sc.nextLine();

    System.out.print("내용 : ");
    String content = Container.sc.nextLine();

    Article article = articleService.write(title, content);

    System.out.printf("%d번 게시물이 작성되었습니다.\n", article.id);
  }

  public void showDetail(Rq rq) {
    int id = rq.getIntParamFromUrlPath(4, 0);

    if(id == 0) {
      System.out.println("올바른 값을 입력해주세요.");
      return;
    }

    Article article = articleService.findById(id);

    if (article == null) {
      System.out.printf("%d번 게시물이 존재하지 않습니다.\n", id);
      return;
    }

    System.out.printf("== %d번 게시물 상세보기 ==\n", article.id);
    System.out.printf("번호 : %d\n", article.id);
    System.out.printf("제목 : %s\n", article.title);
    System.out.printf("내용 : %s\n", article.content);
  }

  public void showList(Rq rq) {
    List<Article> articles = articleService.getArticles();

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

  public void doModify(Rq rq) {
    int id = rq.getIntParamFromUrlPath(4, 0);

    if(id == 0) {
      System.out.println("올바른 값을 입력해주세요.");
      return;
    }

    Article article = articleService.findById(id);

    if (article == null) {
      System.out.printf("%d번 게시물이 존재하지 않습니다.\n", id);
      return;
    }

    System.out.printf("== %d번 게시물 수정 ==\n", article.id);
    System.out.print("수정 할 제목 : ");
    String title = Container.sc.nextLine();

    System.out.print("수정 할 내용 : ");
    String content = Container.sc.nextLine();

    articleService.modify(id, title, content);

    System.out.printf("%d번 게시물이 수정되었습니다.\n", id);
  }

  public void doDelete(Rq rq) {
    int id = rq.getIntParamFromUrlPath(4, 0);

    if(id == 0) {
      System.out.println("올바른 값을 입력해주세요.");
      return;
    }

    Article article = articleService.findById(id);

    if (article == null) {
      System.out.printf("%d번 게시물이 존재하지 않습니다.\n", id);
      return;
    }

    articleService.delete(id);

    System.out.printf("%d번 게시물이 삭제되었습니다.\n", id);
  }
}
