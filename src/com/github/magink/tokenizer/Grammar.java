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

  private static final String END_TOKEN_TYPE = "END";
  // This is the Token Type that will be returned to the user when all tokens have been found.

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

  public int getNumberOfTokenTypes(){
    return types.size();
  }

  public String getEndTokenType() {
    return END_TOKEN_TYPE;
  }
 
  protected TokenType getTokenType(int index) {
    return types.get(index);
  }

  protected Token findMatch(String toMatch) {
    setMatcher(toMatch);
    if(hitEndOfInput(toMatch)) {
      return new Token(END_TOKEN_TYPE, "");
    }  else {
      return foundMatch(toMatch);
    }
  }

  private Token foundMatch(String toMatch) {
    Token longest = null;
    Token current = null;
    if (matcher.find()) {
      for(int i = 0; i < types.size(); i++) {
        TokenType type = getTokenType(i);
        String matchedValue = matcher.group(type.getName());
        if(matchedValue == null) {
          continue;
        }
        current = new Token(type.getName(), matchedValue);
        if (isCurrentLonger(longest, current)) {
          longest = current;
        } 
      }
    }
    if (longest == null) {
      throw new LexicalException("Grammar couldn't match a token.");
    }
    return longest;
  }

  private void setMatcher(String toMatch) {
    if (matcher == null) {
      matcher = pattern.matcher(toMatch);
    }
  }

  private boolean isCurrentLonger(Token longest ,Token current) {
    return longest == null || longest.getValue().length() < current.getValue().length();
  }
  
  private void makePattern() {
    StringBuilder regexes = new StringBuilder();
    for(int i = 0; i < types.size(); i++) {
      if(i > 0) {
        regexes.append("|");
      }
      String token = String.format("(?<%s>%s)", getTokenType(i).getName(), getTokenType(i).getRegex());
      regexes.append(token);
    }
    regexes.append("|\\S");
    pattern = Pattern.compile(regexes.toString());
  }
    
  protected boolean hitEndOfInput (String toMatch) {
    return matcher.hitEnd() || toMatch.length() == 0;
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