package com.container;

import com.domain.article.article.controller.ArticleController;
import com.domain.article.article.service.ArticleService;

import java.util.Scanner;

public class Container {
  public static Scanner sc;

  public static ArticleService articleService;
  public static ArticleController articleController;

  static {
    sc = new Scanner(System.in);

    articleService = new ArticleService();
    articleController = new ArticleController();
  }
}
