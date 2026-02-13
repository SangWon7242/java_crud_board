package com.domain.article.article.service;

import com.container.Container;
import com.domain.article.article.Article;
import com.domain.article.article.repository.ArticleRepository;

import java.util.List;

public class ArticleService {
  private ArticleRepository articleRepository;

  public ArticleService() {
    articleRepository = Container.articleRepository;
  }

  public List<Article> getArticles() {
    return articleRepository.findAll();
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
