package com.github.magink.tokenizer;

public class TokenImp implements Token {
  private String type;
  private String value;
  TokenImp(String type, String value) {
    this.type = type;
    this.value = value;
  }

  @Override
  public String getType() {
    return type;
  }

  @Override
  public String getValue() {
    return value;
  }
  @Override
  public String toString() {
    return "Type: " + type + "\t Value: " + value;
  }
}