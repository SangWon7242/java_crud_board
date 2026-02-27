package com.global.interceptor;

import com.global.rq.Rq;

public class NeedLoginInterceptor implements Interceptor {
  @Override
  public boolean run(Rq rq) {
    if (rq.isLogined()) return true;

    return switch (rq.getActionPath()) {
      case "/usr/article/write",
           "/usr/article/modify",
           "/usr/article/delete",
           "/usr/member/logout",
           "/usr/member/mypage",
           "/usr/board/create",
           "/usr/comment/write",
           "/usr/comment/modify",
           "/usr/comment/delete" -> {
        System.out.println("로그인 후 이용해주세요.");
        yield false;
      }

      default -> true;
    };
  }
}
