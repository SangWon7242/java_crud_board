package com.domain.board.respository;

import com.domain.board.dto.Board;

import java.util.ArrayList;
import java.util.List;

public class BoardRepository {
  private List<Board> boards;

  public BoardRepository() {
    boards = new ArrayList<>();
  }

  public void save(String name, String code) {
    Board board = new Board(name, code);
    boards.add(board);
  }

  public Board findByName(String name) {
    return boards.stream()
        .filter(board -> board.getName().equals(name))
        .findFirst()
        .orElse(null);
  }

  public Board findByCode(String code) {
    return boards.stream()
        .filter(board -> board.getCode().equals(code))
        .findFirst()
        .orElse(null);
  }
}
