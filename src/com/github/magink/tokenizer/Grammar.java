package com.github.magink.tokenizer;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A Grammar is made of one or more types. 
 * A type consists of a name and rules in regex
 * Types can be added using the addType method.
 */
public class Grammar {
  private ArrayList<TokenType> types;
  private Pattern pattern;
  private Matcher matcher;

  public Grammar(){
    types = new ArrayList<>();
  }
  public void addType(String regex, String typeName) {
    if(!typeExist(typeName)) {
      TokenType tokenType = new TokenType(regex, typeName);
      types.add(tokenType);
      makePattern();
    }
  }
  protected int getNumberOfTokenTypes() {
    return types.size();
  } 
  protected TokenType getTokenType(int index) {
    return types.get(index);
  }
  protected Matcher getMatcher(String toMatch) {
    if (matcher == null) {
      matcher = pattern.matcher(toMatch);
    }
    return matcher;
  }
  private void makePattern() {
    StringBuilder regexes = new StringBuilder();
    for(int i = 0; i < getNumberOfTokenTypes(); i++) {
      if(i > 0) {
        regexes.append("|");
      }
      String token = String.format("(?<%s>%s)", getTokenType(i).getName(), getTokenType(i).getRegex());
      regexes.append(token);
    }
    regexes.append("|\\S");
    pattern = Pattern.compile(regexes.toString());
  }
  private boolean typeExist(String name) {
    for (TokenType type : types) {
      if (type.getName().equals(name)) {
        return true;
      } 
    }
    return false;
  }
}