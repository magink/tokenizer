package com.github.magink.tokenizer;

import java.util.ArrayList;
import java.util.Iterator;

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
  
  public Iterator<Token> getIterator() {
    return tokens.iterator();
  }

  protected void addToken(Token toAdd) {
    tokens.add(toAdd);
  }

}
