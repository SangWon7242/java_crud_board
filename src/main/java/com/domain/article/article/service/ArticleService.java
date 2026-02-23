package com.domain.article.article.service;

import com.global.container.Container;
import com.domain.article.article.dto.Article;
import com.domain.article.article.repository.ArticleRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ArticleService {
  private ArticleRepository articleRepository;

  public ArticleService() {
    articleRepository = Container.getArticleRepository();
  }

  public List<Article> getArticles(String orderBy, String searchKeyword) {
    return articleRepository.findAll(orderBy, searchKeyword);
  }

  public Article write(String title, String content) {
    return articleRepository.save(title, content);
  }

  public void modify(int id, String title, String content) {
    articleRepository.modify(id, title, content);
  }

  public void delete(int id) {
    articleRepository.delete(id);
  }

  // id를 기반으로 게시물을 찾는 메서드
  public Article findById(int id) {
    return articleRepository.findById(id);
  }
}
