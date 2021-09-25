package com.github.magink.tokenizer;

import java.util.ArrayList;
import java.util.regex.Matcher;

/**
 * Tokenizer analyses sections of a String input and
 * classifies it according to the rules of a Grammar input. 
 * The user can either request the active token, step forwards or backwards to 
 * get the next or previous token. When user has reached the end of the String input 
 * a special END token is returned.
 */

public class Tokenizer {

  private static final String END_TOKEN_TYPE = "END";

  private int offset = 0;
  private String toMatch = "";
  private int activeToken = 0;
  private Grammar grammar; 
  private ArrayList<Token> tokens = new ArrayList<>();

  // Write JavaDocs for all public methods
  public Tokenizer(Grammar grammar, String input) throws LexicalException {
    this.toMatch = input;
    this.grammar = grammar;
    findNextToken(); 
  }
  public String getActiveToken() {
    return tokens.get(activeToken).toString();
  }
  public String getActiveTokenValue() {
    return tokens.get(activeToken).getValue();
  }
  public String getActiveTokenType() {
    return tokens.get(activeToken).getType();
  }
  public void nextToken() throws LexicalException {
    if (!hasEndToken()) {
      findNextToken();
      activeToken++;
    }
  }
  public void previousToken() {
    if(activeToken > 0) {
      activeToken--;
    }
  }

  private void findNextToken () throws LexicalException {
    Token nextToken = null;
    nextToken = findMatch();
    tokens.add(nextToken);
  }

  private Token findMatch() throws LexicalException {
    Matcher matcher = grammar.getMatcher(toMatch);
    Token token = null;
    if(matcher.hitEnd() || toMatch.length() == 0) {
      token = new Token(END_TOKEN_TYPE, "");
    } 
    else if (matcher.find()) {
      System.out.println("Full match: " + matcher.group(0));            
        for (int i = 1; i <= matcher.groupCount(); i++) {
            System.out.println("Group " + i + ": " + matcher.group(i));
        }

      Token longest = null;
      Token current = null;
      for(int i = 0; i < grammar.getNumberOfTokenTypes(); i++) {
        TokenType type = grammar.getTokenType(i);
        String matchedValue = matcher.group(type.getName());
        if(matchedValue == null) {
          continue;
        } else {
          current = new Token(type.getName(), matchedValue);
        if (isCurrentLonger(longest, current)) {
          longest = current;
        }
      }
      token = longest;
    }
    if (token == null) {
      throw new LexicalException("Grammar didn't match input.");
    }
    return token;
  }

  private boolean isCurrentLonger(Token longest ,Token current) {
    return longest == null || longest.getValue().length() < current.getValue().length();
  }
  private boolean hasEndToken() {
    return tokens
      .get(tokens.size() -1)
      .getType()
      .equals(END_TOKEN_TYPE);
  }
}