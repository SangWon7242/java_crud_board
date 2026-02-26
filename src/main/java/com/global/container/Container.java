package com.global.container;

import com.domain.article.article.controller.ArticleController;
import com.domain.article.article.repository.ArticleRepository;
import com.domain.article.article.service.ArticleService;
import com.domain.board.controllor.BoardController;
import com.domain.board.respository.BoardRepository;
import com.domain.board.service.BoardService;
import com.domain.member.controller.MemberController;
import com.domain.member.repository.MemberRepository;
import com.domain.member.service.MemberService;
import com.global.interceptor.NeedLoginInterceptor;
import com.global.interceptor.NeedLogoutInterceptor;
import com.global.session.Session;
import lombok.Getter;

import java.util.Scanner;

public class Container {
  @Getter
  private static Scanner sc;

  @Getter
  private static Session session;

  @Getter
  private static NeedLoginInterceptor needLoginInterceptor;
  @Getter
  private static NeedLogoutInterceptor needLogoutInterceptor;

  @Getter
  private static BoardRepository boardRepository;

  @Getter
  private static MemberRepository memberRepository;
  @Getter
  private static ArticleRepository articleRepository;

  @Getter
  private static BoardService boardService;
  @Getter
  private static MemberService memberService;
  @Getter
  private static ArticleService articleService;

  @Getter
  private static BoardController boardController;
  @Getter
  private static MemberController memberController;
  @Getter
  private static ArticleController articleController;

  static {
    sc = new Scanner(System.in);
    session = new Session();

    needLoginInterceptor = new NeedLoginInterceptor();
    needLogoutInterceptor = new NeedLogoutInterceptor();

    boardRepository = new BoardRepository();
    memberRepository = new MemberRepository();
    articleRepository = new ArticleRepository();

    boardService = new BoardService();
    memberService = new MemberService();
    articleService = new ArticleService();

    boardController = new BoardController();
    memberController = new MemberController();
    articleController = new ArticleController();
  }
}
