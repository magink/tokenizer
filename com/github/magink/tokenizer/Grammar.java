package com.github.magink.tokenizer;

import java.util.ArrayList;

/**
 * A Grammar is made of one or more types. 
 * A type consists of a name and the 
 * rules in regex
 */
public class Grammar {
  private ArrayList<TokenType> types;

  public Grammar(){
    types = new ArrayList<>();
  }
  public void addType(String regex, String name) {
    TokenType tokenType = new TokenType(regex, name);
    if(!types.contains(tokenType)) {
      types.add(tokenType);
    }
  }
  public int getNumberOfTokenTypes() {
    return types.size();
  } 
  public TokenType getTokenType(int index) {
    return types.get(index);
  }
}