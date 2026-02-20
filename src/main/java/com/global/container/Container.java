package com.global.container;

import com.domain.article.article.controller.ArticleController;
import com.domain.article.article.repository.ArticleRepository;
import com.domain.article.article.service.ArticleService;
import lombok.Getter;

import java.util.Scanner;

public class Container {
  @Getter
  private static Scanner sc;
  @Getter
  private static ArticleRepository articleRepository;
  @Getter
  private static ArticleService articleService;
  @Getter
  private static ArticleController articleController;

  static {
    sc = new Scanner(System.in);

    articleRepository = new ArticleRepository();
    articleService = new ArticleService();
    articleController = new ArticleController();
  }
}
