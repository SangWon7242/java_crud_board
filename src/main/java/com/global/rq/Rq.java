package com.global.rq;

import com.global.container.Container;
import com.global.session.Session;
import com.global.util.Util;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

public class Rq {
  private String url;
  @Getter
  private Map<String, String> params;
  @Getter
  private String urlPath;
  private Session session;
  private String loginedMemberId;

  @Getter
  @Setter
  String controllerTypeCode;
  @Getter
  @Setter
  String controllerName;
  @Getter
  @Setter
  String actionMethodName;

  public Rq(String url) {
    this.url = url;
    this.params = Util.getParamsFromUrl(this.url);
    this.urlPath = Util.getPathFromUrl(this.url);
    this.session = Container.getSession();
    this.loginedMemberId = "loginedMemberId";
  }

  public String getCmd() {
    return url;
  }

  public String getActionPath() {
    String[] bits = getUrlPathBits();

    if (bits.length < 4) return urlPath;

    controllerTypeCode = bits[1];
    controllerName = bits[2];
    actionMethodName = bits[3];

    return "/%s/%s/%s".formatted(controllerTypeCode, controllerName, actionMethodName);
  }

  public String[] getUrlPathBits() {
    return urlPath.split("/");
  }

  public int getIntParamFromUrlPath(int index, int defaultValue) {
    String[] bits = getUrlPathBits();

    if (index < 0 || index >= bits.length) return defaultValue;

    try {
      return Integer.parseInt(bits[index]);
    } catch (NumberFormatException e) {
      return defaultValue;
    }
  }

  public String getParam(String paramName, String defaultValue) {
    if (!params.containsKey(paramName)) return defaultValue;

    return params.get(paramName);
  }

  public void login(String value) {
    setAttr(loginedMemberId, value);
  }

  public void logout() {
    removeAttr(loginedMemberId);
  }

  // 로그인 여부 확인
  public boolean isLogined() {
    return hasAttr(loginedMemberId);
  }

  public boolean isNotLogined() {
    return !isLogined();
  }

  public String getLoginedMemberId() {
    return getAttr(loginedMemberId);
  }

  public void setAttr(String key, String value) {
    session.setAttribute(key, value);
  }

  public String getAttr(String key) {
    return session.getAttribute(key);
  }

  public boolean hasAttr(String key) {
    return session.hasAttribute(key);
  }

  public void removeAttr(String key) {
    session.removeAttribute(key);
  }
}
