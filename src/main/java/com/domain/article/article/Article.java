package com.domain.article.article;

public class Article {
  public static int lastId;
  public int id;
  public String title;
  public String content;

  static {
    lastId = 0;
  }

  public Article(String title, String content) {
    this(++lastId, title, content);
  }

  public Article(int id, String title, String content) {
    this.id = id;
    this.title = title;
    this.content = content;
  }

  @Override
  public String toString() {
    return "{id: %d, title: '%s', content: '%s'}".formatted(id, title, content);
  }
}
