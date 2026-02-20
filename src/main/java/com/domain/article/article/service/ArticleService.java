package com.domain.article.article.service;

import com.global.container.Container;
import com.domain.article.article.dto.Article;
import com.domain.article.article.repository.ArticleRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ArticleService {
  private ArticleRepository articleRepository;

  public ArticleService() {
    articleRepository = Container.articleRepository;
  }

  public List<Article> getArticles(String orderBy) {
    // articleRepository.findAll() 메서드를 통해 가져온 원본 리스트를 바탕으로 새로운 리스트 객체 생성
    List<Article> articles = new ArrayList<>(articleRepository.findAll());

    if("idAsc".equals(orderBy)) {
      // Article 객체의 id 값을 기준으로 오름차순 정렬
      articles.sort(Comparator.comparingInt(Article::getId));
    } else {
      articles.sort(Comparator.comparingInt(Article::getId).reversed());
    }
    
    return articles;
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
