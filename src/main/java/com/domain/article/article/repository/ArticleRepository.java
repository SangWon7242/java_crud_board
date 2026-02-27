package com.domain.article.article.repository;

import com.domain.article.article.dto.Article;
import com.domain.board.dto.Board;
import com.domain.member.dto.Member;
import com.global.container.Container;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ArticleRepository {
  public List<Article> articles;

  public ArticleRepository() {
    articles = new ArrayList<>();

    testData();
  }

  void testData() {
    IntStream.rangeClosed(1, 100)
        .forEach(i -> {
          int randomMemberId = (int) (Math.random() * 3) + 1;
          int randomBoardId = (int) (Math.random() * 2) + 1;
          save("제목" + i, "내용" + i, randomMemberId, randomBoardId);
        });
  }

  private List<Article> filteredArticles(int boardId, String typeCode, String keyword) {
    List<Article> searchResults = articles;

    if (boardId != 0) {
      searchResults = searchResults.stream()
          .filter(article -> article.getBoardId() == boardId)
          .collect(Collectors.toList());
    }

    if (!keyword.isEmpty()) {
      List<Article> finalSearchResults = searchResults;
      searchResults = switch (typeCode) {
        case "title" -> finalSearchResults.stream()
            .filter(article -> article.getTitle().contains(keyword))
            .collect(Collectors.toList());
        case "content" -> finalSearchResults.stream()
            .filter(article -> article.getContent().contains(keyword))
            .collect(Collectors.toList());
        default -> finalSearchResults.stream()
            .filter(article -> article.getTitle().contains(keyword) || article.getContent().contains(keyword))
            .collect(Collectors.toList());
      };
    }

    return searchResults;
  }

  private List<Article> sortedArticles(List<Article> articles, String orderBy) {
    List<Article> sortedArticles = new ArrayList<>(articles);

    // 정렬 로직
    if ("idAsc".equals(orderBy)) {
      // Article 객체의 id 값을 기준으로 오름차순 정렬
      sortedArticles.sort(Comparator.comparingInt(Article::getId));
    } else {
      sortedArticles.sort(Comparator.comparingInt(Article::getId).reversed());
    }

    return sortedArticles;
  }

  public List<Article> findAll() {
    return articles;
  }

  public List<Article> findAll(int boardId, String orderBy, String typeCode, String keyword) {
    // 검색 수행
    List<Article> searchResults = filteredArticles(boardId, typeCode, keyword);

    // 정렬 수행
    return sortedArticles(searchResults, orderBy);
  }

  public Article save(String title, String content, int memberId, int boardId) {
    Member member = Container.getMemberRepository().findById(memberId);
    Board board = Container.getBoardRepository().findById(boardId);

    LocalDateTime createDate = LocalDateTime.now();
    LocalDateTime updateDate = createDate;

    Article article = new Article(createDate, updateDate, title, content, memberId, member.getName(), boardId, board.getName(), 0);
    articles.add(article);

    return article;
  }

  public void modify(int id, String title, String content) {
    Article article = findById(id);

    article.setUpdateDate(LocalDateTime.now());
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

  public void increaseHit(int id) {
    Article article = findById(id);
    article.setHit(article.getHit() + 1);
  }
}
