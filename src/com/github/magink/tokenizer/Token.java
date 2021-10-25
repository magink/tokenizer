package com.github.magink.tokenizer;

public interface Token {

  /**
   * @return the type of this token, "Number" for the value 42
   */
  public String getType();

  /**
   * @return the value of this token, example "42" for a Number token
   */
  public String getValue();
}
