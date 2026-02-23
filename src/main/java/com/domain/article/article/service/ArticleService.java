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

  // 검색 키워드를 기반으로 한 게시물 리스트 필터링
  private List<Article> getSearchResults(List<Article> articles, String searchKeyword) {
    if(!searchKeyword.isEmpty()) {
      articles = articles.stream()
          .filter(article -> article.getTitle().contains(searchKeyword) || article.getContent().contains(searchKeyword))
          .collect(Collectors.toList());
    }

    return articles;
  }

  public List<Article> getArticles(String orderBy, String searchKeyword) {
    // articleRepository.findAll() 메서드를 통해 가져온 원본 리스트를 바탕으로 새로운 리스트 객체 생성
    List<Article> articles = new ArrayList<>(articleRepository.findAll());
    List<Article> searchResults = getSearchResults(articles, searchKeyword);

    // 정렬 로직
    if("idAsc".equals(orderBy)) {
      // Article 객체의 id 값을 기준으로 오름차순 정렬
      searchResults.sort(Comparator.comparingInt(Article::getId));
    } else {
      searchResults.sort(Comparator.comparingInt(Article::getId).reversed());
    }
    
    return searchResults;
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
