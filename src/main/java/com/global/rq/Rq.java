package com.global.rq;

import com.global.util.Util;

import java.util.Map;

public class Rq {
  private String url;
  private Map<String, Object> params;
  private String urlPath;

  public Rq(String url) {
    this.url = url;
    this.params = Util.getParamsFromUrl(this.url);
    this.urlPath = Util.getPathFromUrl(this.url);
  }

  public String getCmd() {
    return url;
  }

  public Map<String, Object> getParams() {
    return params;
  }

  public String getUrlPath() {
    return urlPath;
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
}
