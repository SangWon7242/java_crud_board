package com.domain.article.article.service;

import com.domain.article.article.Article;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ArticleService {
  public List<Article> articles;
  public int lastArticleId;

  public ArticleService() {
    articles = new ArrayList<>();

    makeArticleTestData();

    lastArticleId = articles.get(articles.size() - 1).id;
  }

  void makeArticleTestData() {
    IntStream.rangeClosed(1, 3)
        .forEach(i -> articles.add(new Article(i, "제목" + i, "내용" + i)));
  }

  public List<Article> getArticles() {
    return articles;
  }

  public Article write(String title, String content) {
    Article article = new Article(title, content);
    articles.add(article);

    return article;
  }

  public void modify(int id, String title, String content) {
    Article article = findById(id);
    article.title = title;
    article.content = content;
  }

  public void delete(int id) {
    Article article = findById(id);
    articles.remove(article);
  }

  // id를 기반으로 게시물을 찾는 메서드
  public Article findById(int id) {
    return articles.stream()
        .filter(article -> article.id == id)
        .findFirst() // 찾은 것중에 첫 번째 데이터 가져와라
        .orElse(null); // 못 찾은 경우에는 null을 넣어라
  }
}
