package com.github.magink.tokenizer;

 class TokenType {
  private String name;
  private String regex;
  protected TokenType(String regex, String name) {
    this.name = name;
    this.regex = regex;
  }
  protected String getTokenTypePattern() {
    return String.format("(?<%s>%s)", name, regex);
  }
  protected String getName() {
    return name;
  }
}
