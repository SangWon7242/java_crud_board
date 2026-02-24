package com.global.container;

import com.domain.article.article.controller.ArticleController;
import com.domain.article.article.repository.ArticleRepository;
import com.domain.article.article.service.ArticleService;
import com.domain.member.controller.MemberController;
import com.domain.member.repository.MemberRepository;
import com.domain.member.service.MemberService;
import com.global.session.Session;
import lombok.Getter;

import java.util.Scanner;

public class Container {
  @Getter
  private static Scanner sc;

  @Getter
  private static Session session;

  @Getter
  private static MemberRepository memberRepository;
  @Getter
  private static ArticleRepository articleRepository;

  @Getter
  private static MemberService memberService;
  @Getter
  private static ArticleService articleService;

  @Getter
  private static MemberController memberController;
  @Getter
  private static ArticleController articleController;

  static {
    sc = new Scanner(System.in);
    session = new Session();

    memberRepository = new MemberRepository();
    articleRepository = new ArticleRepository();

    memberService = new MemberService();
    articleService = new ArticleService();

    memberController = new MemberController();
    articleController = new ArticleController();
  }
}
