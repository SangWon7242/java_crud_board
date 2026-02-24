package com.global.session;

import java.util.HashMap;
import java.util.Map;

public class Session {
  private Map<String, String> sessionStore;

  public Session() {
    sessionStore = new HashMap<>();
  }

  // 세션 데이터 저장
  public void setAttribute(String key, String value) {
    sessionStore.put(key, value);
  }

  // 세션 데이터 조회
  public String getAttribute(String key) {
    return sessionStore.get(key);
  }

  // 세션 데이터 존재여부
  public boolean hasAttribute(String key) {
    return sessionStore.containsKey(key);
  }

  // 세션 데이터 삭제
  public void removeAttribute(String key) {
    sessionStore.remove(key);
  }
}
