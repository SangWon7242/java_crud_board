package com.container;

import com.domain.article.article.controller.ArticleController;
import com.domain.article.article.repository.ArticleRepository;
import com.domain.article.article.service.ArticleService;

import java.util.Scanner;

public class Container {
  public static Scanner sc;

  public static ArticleRepository articleRepository;
  public static ArticleService articleService;
  public static ArticleController articleController;

  static {
    sc = new Scanner(System.in);

    articleRepository = new ArticleRepository();
    articleService = new ArticleService();
    articleController = new ArticleController();
  }
}
