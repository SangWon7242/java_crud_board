package com.global.rq;

import com.global.util.Util;

import java.util.Map;

public class Rq {
  private String url;
  private Map<String, String> params;
  private String urlPath;

  public Rq(String url) {
    this.url = url;
    this.params = Util.getParamsFromUrl(this.url);
    this.urlPath = Util.getPathFromUrl(this.url);
  }

  public String getCmd() {
    return url;
  }

  public Map<String, String> getParams() {
    return params;
  }

  public String getUrlPath() {
    return urlPath;
  }

  public String getActionPath() {
    String[] bits = getUrlPathBits();
    // /usr/article/detail/1
    // ["", "usr", "article", "detail", "1"]
    // /usr/article/list
    // ["", "usr", "article", "list"]

    if(bits.length <= 4) return urlPath;

    return "/" + bits[1] + "/" + bits[2] + "/" + bits[3];
  }

  public String[] getUrlPathBits() {
    return urlPath.split("/");
  }

  public int getIntParamFromUrlPath(int index, int defaultValue) {
    String[] bits = getUrlPathBits();

    if(index < 0 || index >= bits.length) return defaultValue;

    try {
      return Integer.parseInt(bits[index]);
    } catch (NumberFormatException e) {
      return defaultValue;
    }
  }

  public String getParam(String paramName, String defaultValue) {
    if(!params.containsKey(paramName)) return defaultValue;

    return params.get(paramName);
  }
}
