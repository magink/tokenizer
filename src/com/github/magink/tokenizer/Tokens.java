package com.github.magink.tokenizer;

import java.util.ArrayList;
import java.util.stream.Stream;

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
  
  public Stream<Token> getStream() {
    return tokens.stream();
  }

  protected void addToken(Token toAdd) {
    tokens.add(toAdd);
  }

}
