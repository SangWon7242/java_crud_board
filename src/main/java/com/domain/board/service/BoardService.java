package com.domain.board.service;

import com.domain.board.dto.Board;
import com.domain.board.respository.BoardRepository;
import com.global.container.Container;

public class BoardService {
  private BoardRepository boardRepository;

  public BoardService() {
    boardRepository = Container.getBoardRepository();
  }

  public void create(String name, String code) {
    boardRepository.save(name, code);
  }

  public Board findByName(String name) {
    return boardRepository.findByName(name);
  }

  public Board findByCode(String code) {
    return boardRepository.findByCode(code);
  }

  public Board findById(int id) {
    return boardRepository.findById(id);
  }
}
