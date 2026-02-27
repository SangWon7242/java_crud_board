package com.domain.article.article.service;

import com.global.container.Container;
import com.domain.article.article.dto.Article;
import com.domain.article.article.repository.ArticleRepository;

import java.util.List;

public class ArticleService {
  private ArticleRepository articleRepository;

  public ArticleService() {
    articleRepository = Container.getArticleRepository();
  }

  public List<Article> getArticles(int boardId, String orderBy, String typeCode, String keyword) {
    return articleRepository.findAll(boardId, orderBy, typeCode, keyword);
  }

  public Article write(String title, String content, int memberId, int boardId) {
    return articleRepository.save(title, content, memberId, boardId);
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

  public void increaseHit(int id) {
    articleRepository.increaseHit(id);
  }
}
