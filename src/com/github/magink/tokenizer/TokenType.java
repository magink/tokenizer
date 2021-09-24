package com.github.magink.tokenizer;

public class TokenType {
  private String name;
  private String regex;
  protected TokenType(String regex, String name) {
    this.name = name;
    this.regex = regex;
  }
  public String getRegex() {
    return regex;
  }
  public String getName() {
    return name;
  }
}
