package com.github.magink.tokenizer;

import java.util.ArrayList;
import java.util.stream.Stream;

public class Tokens {
  private ArrayList<TokenImp> tokens;
  
  public Tokens() {
    tokens = new ArrayList<>();
  }

  public TokenImp getToken(int index) {
    return tokens.get(index);
  }

  public int size() {
    return tokens.size();
  }
  
  public Stream<TokenImp> getStream() {
    return tokens.stream();
  }

  protected void addToken(TokenImp toAdd) {
    tokens.add(toAdd);
  }

}
