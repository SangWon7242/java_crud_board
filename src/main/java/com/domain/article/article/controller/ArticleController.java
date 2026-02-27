package com.domain.article.article.controller;

import com.domain.board.dto.Board;
import com.domain.board.service.BoardService;
import com.domain.member.dto.Member;
import com.global.container.Container;
import com.domain.article.article.dto.Article;
import com.domain.article.article.service.ArticleService;
import com.global.controller.Controller;
import com.global.rq.Rq;

import java.util.List;

public class ArticleController implements Controller {
  private BoardService boardService;
  private ArticleService articleService;

  public ArticleController() {
    boardService = Container.getBoardService();
    articleService = Container.getArticleService();
  }

  @Override
  public void performAction(Rq rq) {
    if (rq.getActionMethodName().equals("write")) {
      doWrite(rq);
    } else if (rq.getActionMethodName().equals("detail")) {
      showDetail(rq);
    } else if (rq.getActionMethodName().equals("list")) {
      showList(rq);
    } else if (rq.getActionMethodName().equals("modify")) {
      doModify(rq);
    } else if (rq.getActionMethodName().equals("delete")) {
      doDelete(rq);
    }
  }

  public void doWrite(Rq rq) {
    int boardId = rq.getIntParamFromUrlPath(5, 1);

    Board board = boardService.findById(boardId);

    if (board == null) {
      System.out.println("존재하지 않는 게시판입니다.");
      return;
    }

    System.out.printf("== '%s 게시판' 게시물 작성 ==\n", board.getName());
    System.out.print("제목 : ");
    String title = Container.getSc().nextLine();

    System.out.print("내용 : ");
    String content = Container.getSc().nextLine();

    Member member = rq.getLoginedMember();
    Article article = articleService.write(title, content, member.getId(), board.getId());

    System.out.printf("%d번 게시물이 작성되었습니다.\n", article.getId());
  }

  public void showDetail(Rq rq) {
    int id = rq.getIntParamFromUrlPath(4, 0);

    if (id == 0) {
      System.out.println("올바른 값을 입력해주세요.");
      return;
    }

    Article article = articleService.findById(id);

    if (article == null) {
      System.out.printf("%d번 게시물이 존재하지 않습니다.\n", id);
      return;
    }

    System.out.printf("== %d번 게시물 상세보기 ==\n", article.getId());
    System.out.printf("번호 : %d\n", article.getId());
    System.out.printf("제목 : %s\n", article.getTitle());
    System.out.printf("내용 : %s\n", article.getContent());
    System.out.printf("작성자 : %s\n", article.getWriterName());
  }

  public void showList(Rq rq) {
    String orderBy = rq.getParam("orderBy", "idDesc");
    String keywordTypeCode = rq.getParam("keywordTypeCode", "all");
    String searchKeyword = rq.getParam("searchKeyword", "");

    List<Article> articles = articleService.getArticles(orderBy, keywordTypeCode, searchKeyword);

    if (articles.isEmpty()) {
      System.out.println("게시물이 존재하지 않습니다.");
      return;
    }

    System.out.println("== 게시물 리스트 ==");
    System.out.println("번호 | 제목 | 작성자");

    articles.forEach(article ->
        System.out.printf("%d | %s | %s\n", article.getId(), article.getTitle(), article.getWriterName())
    );
  }

  public void doModify(Rq rq) {
    int id = rq.getIntParamFromUrlPath(4, 0);

    if (id == 0) {
      System.out.println("올바른 값을 입력해주세요.");
      return;
    }

    Member member = rq.getLoginedMember();
    Article article = articleService.findById(id);

    if (article == null) {
      System.out.printf("%d번 게시물이 존재하지 않습니다.\n", id);
      return;
    }

    if(article.getMemberId() != member.getId()) {
      System.out.println("권한이 없습니다.");
      return;
    }

    System.out.printf("== %d번 게시물 수정 ==\n", article.getId());
    System.out.print("수정 할 제목 : ");
    String title = Container.getSc().nextLine();

    System.out.print("수정 할 내용 : ");
    String content = Container.getSc().nextLine();

    articleService.modify(id, title, content);

    System.out.printf("%d번 게시물이 수정되었습니다.\n", id);
  }

  public void doDelete(Rq rq) {
    int id = rq.getIntParamFromUrlPath(4, 0);

    if (id == 0) {
      System.out.println("올바른 값을 입력해주세요.");
      return;
    }

    Member member = rq.getLoginedMember();
    Article article = articleService.findById(id);

    if (article == null) {
      System.out.printf("%d번 게시물이 존재하지 않습니다.\n", id);
      return;
    }

    if(article.getMemberId() != member.getId()) {
      System.out.println("권한이 없습니다.");
      return;
    }

    articleService.delete(id);

    System.out.printf("%d번 게시물이 삭제되었습니다.\n", id);
  }
}
