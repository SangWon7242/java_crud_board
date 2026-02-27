package com.domain.article.comment.controller;

import com.domain.article.article.dto.Article;
import com.domain.article.article.service.ArticleService;
import com.domain.article.comment.dto.Comment;
import com.domain.article.comment.service.CommentService;
import com.domain.member.dto.Member;
import com.global.container.Container;
import com.global.controller.Controller;
import com.global.rq.Rq;

public class CommentController implements Controller {
  private CommentService commentService;
  private ArticleService articleService;

  public CommentController() {
    this.commentService = Container.getCommentService();
    this.articleService = Container.getArticleService();
  }

  @Override
  public void performAction(Rq rq) {
    if (rq.getActionMethodName().equals("write")) {
      doWrite(rq);
    } else if (rq.getActionMethodName().equals("modify")) {
      doModify(rq);
    } else if (rq.getActionMethodName().equals("delete")) {
      doDelete(rq);
    }
  }

  private void doWrite(Rq rq) {
    int articleId = rq.getIntParamFromUrlPath(4, 0);

    if (articleId == 0) {
      System.out.println("올바른 게시물 번호를 입력해주세요.");
      return;
    }

    Article article = articleService.findById(articleId);

    if (article == null) {
      System.out.println("존재하지 않는 게시물입니다.");
      return;
    }

    System.out.printf("== %d번 게시물 댓글 작성 == ", articleId);
    System.out.print("내용 : ");
    String body = Container.getSc().nextLine();

    if (body.trim().isEmpty()) {
      System.out.println("내용을 입력해주세요.");
      return;
    }

    Member member = rq.getLoginedMember();
    commentService.write(articleId, member.getId(), member.getName(), body);

    System.out.println("댓글이 작성되었습니다.");
  }

  private void doModify(Rq rq) {
    int id = rq.getIntParamFromUrlPath(4, 0);

    if (id == 0) {
      System.out.println("올바른 댓글 번호를 입력해주세요.");
      return;
    }

    Comment comment = commentService.findById(id);

    if (comment == null) {
      System.out.println("존재하지 않는 댓글입니다.");
      return;
    }

    Member member = rq.getLoginedMember();
    if (comment.getMemberId() != member.getId()) {
      System.out.println("권한이 없습니다.");
      return;
    }

    System.out.print("수정 할 내용 : ");
    String body = Container.getSc().nextLine();

    if (body.trim().isEmpty()) {
      System.out.println("내용을 입력해주세요.");
      return;
    }

    commentService.modify(id, body);
    System.out.println("댓글이 수정되었습니다.");
  }

  private void doDelete(Rq rq) {
    int id = rq.getIntParamFromUrlPath(4, 0);

    if (id == 0) {
      System.out.println("올바른 댓글 번호를 입력해주세요.");
      return;
    }

    Comment comment = commentService.findById(id);

    if (comment == null) {
      System.out.println("존재하지 않는 댓글입니다.");
      return;
    }

    Member member = rq.getLoginedMember();
    if (comment.getMemberId() != member.getId()) {
      System.out.println("권한이 없습니다.");
      return;
    }

    commentService.delete(comment);
    System.out.printf("%d번 댓글이 삭제되었습니다.", id);
  }
}
