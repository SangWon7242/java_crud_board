package com.domain.article.article.repository;

import com.domain.article.article.dto.Article;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ArticleRepository {
  public List<Article> articles;
  public int lastArticleId;

  public ArticleRepository() {
    articles = new ArrayList<>();

    makeArticleTestData();

    lastArticleId = articles.get(articles.size() - 1).getId();
  }

  void makeArticleTestData() {
    IntStream.rangeClosed(1, 3)
        .forEach(i -> save("제목" + i, "내용" + i));
  }

  public List<Article> findAll() {
    return articles;
  }

  public Article save(String title, String content) {
    Article article = new Article(title, content);
    articles.add(article);

    return article;
  }

  public void modify(int id, String title, String content) {
    Article article = findById(id);
    article.setTitle(title);
    article.setContent(content);
  }

  public void delete(int id) {
    Article article = findById(id);
    articles.remove(article);
  }

  public Article findById(int id) {
    return articles.stream()
        .filter(article -> article.getId() == id)
        .findFirst() // 찾은 것중에 첫 번째 데이터 가져와라
        .orElse(null); // 못 찾은 경우에는 null을 넣어라
  }
}
