package com.github.magink.tokenizer;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A Grammar is made of one or more types. 
 * A type consists of a name and the 
 * rules in regex
 */
public class Grammar {
  private ArrayList<TokenType> types;
  private Pattern pattern;
  private Matcher matcher;

  public Grammar(){
    types = new ArrayList<>();
  }
  public void addType(String regex, String name) {
    TokenType tokenType = new TokenType(regex, name);
    if(!types.contains(tokenType)) {
      types.add(tokenType);
    }
    makePattern();
  }
  public int getNumberOfTokenTypes() {
    return types.size();
  } 
  public TokenType getTokenType(int index) {
    return types.get(index);
  }
  public Matcher getMatcher(String toMatch) {
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
}