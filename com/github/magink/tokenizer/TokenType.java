package com.github.magink.tokenizer;

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class TokenType {
  private Pattern pattern;
  private String name;
  protected TokenType(String regex, String name) throws PatternSyntaxException {
    this.name = name;
    pattern = Pattern.compile(regex);
  }
  public Pattern getPattern() {
    return pattern;
  }
  public String getName() {
    return name;
  }
}
