package com.github.magink.tokenizer;

import java.util.ArrayList;

public class Tokens {
  private ArrayList<Token> tokens;
  
  public Tokens() {
    tokens = new ArrayList<>();
  }

  public Token getToken(int index) {
    return tokens.get(index);
  }

  public int size() {
    return tokens.size();
  }
  
  protected void addToken(Token toAdd) {
    tokens.add(toAdd);
  }

}
