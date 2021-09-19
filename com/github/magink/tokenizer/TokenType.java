package com.github.magink.tokenizer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TokenType {
  private String name;
  private String regex;
  private Matcher matcher;
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
  public Matcher getMatcher(String toMatch) {
    if (matcher == null) {
      matcher = Pattern.compile(regex).matcher(toMatch);
    }
    return matcher;
  }
}
