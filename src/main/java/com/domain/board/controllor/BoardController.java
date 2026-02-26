package com.domain.board.controllor;

import com.domain.board.dto.Board;
import com.domain.board.service.BoardService;
import com.domain.member.dto.Member;
import com.global.container.Container;
import com.global.controller.Controller;
import com.global.rq.Rq;

public class BoardController implements Controller {
  private BoardService boardService;

  public BoardController() {
    boardService = Container.getBoardService();
  }

  @Override
  public void performAction(Rq rq) {
    if (rq.getActionMethodName().equals("create")) {
      doCreate(rq);
    }
  }

  private void doCreate(Rq rq) {
    String name;
    String code;
    Board board;

    Member member = rq.getLoginedMember();

    if (!member.isAdmin()) {
      System.out.println("관리자만 게시판을 생성할 수 있습니다.");
      return;
    }

    System.out.println("== 게시판 생성 ==");

    while (true) {
      System.out.print("게시판 이름 : ");
      name = Container.getSc().nextLine();

      if (name.trim().isEmpty()) {
        System.out.println("게시판 이름을 입력해주세요.");
        continue;
      }

      board = boardService.findByName(name);

      if (board != null) {
        System.out.println("이미 존재하는 게시판 이름입니다. 다른 이름을 입력해주세요.");
        continue;
      }

      break;
    }

    System.out.println("== 게시판 코드 ==");
    System.out.println("== 게시판 코드를 입력 예시 ==");
    System.out.println("(free, notice, qna 등 영문 소문자 권장)");

    while (true) {
      System.out.print("코드 입력 : ");
      code = Container.getSc().nextLine();

      if(code.trim().isEmpty()) {
        System.out.println("게시판 코드를 입력해주세요.");
        continue;
      }

      board = boardService.findByCode(code);

      if (board != null) {
        System.out.println("이미 존재하는 게시판 코드입니다. 다른 코드를 입력해주세요.");
        continue;
      }

      break;
    }

    boardService.create(name, code);

    System.out.printf("%s 게시판이 생성되었습니다.\n", name);
  }
}
