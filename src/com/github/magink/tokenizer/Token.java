package com.github.magink.tokenizer;

class Token {
  private String type;
  private String value;
  Token(String type, String value) {
    this.type = type;
    this.value = value;
  }
  public String getType() {
    return type;
  }
  public String getValue() {
    return value;
  }
  @Override
  public String toString() {
    return "Type: " + type + "\t Value: " + value;
  }
}