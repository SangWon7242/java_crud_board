package com.global.rq;

public class Rq {
  private String cmd;
  private String urlPath;

  public Rq(String cmd) {
    this.cmd = cmd;
    urlPath = cmd;
  }

  public String getCmd() {
    return cmd;
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
