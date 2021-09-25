package com.github.magink.tokenizer;

import java.util.ArrayList;
import java.util.regex.Matcher;

/**
 * Tokenizer analyses a String input and classifies it according to the rules of a Grammar input. 
 * The user can either request the active token, step forwards or backwards to 
 * get the next or previous token. 
 * When user has reached the end of the String input a special END token is returned.
 */

public class Tokenizer {

  private static final String END_TOKEN_TYPE = "END";
  // This is the Token Type that will be returned to the user when all tokens have been found.

  private String toMatch = "";
  private int activeToken = 0;
  private Grammar grammar; 
  private ArrayList<Token> tokens = new ArrayList<>();

  /**
   * @param grammar Object that contain token types and regex rules.
   * @param input The string to tokenize. 
   * @throws LexicalException if grammar can't find a token.
   */
  public Tokenizer(Grammar grammar, String input) throws LexicalException {
    this.toMatch = input;
    this.grammar = grammar;
    findNextToken(); 
  }
  /**
   * @return Both value and type  
   */
  public String getActiveToken() {
    return tokens.get(activeToken).toString();
  }
  public String getActiveTokenValue() {
    return tokens.get(activeToken).getValue();
  }
  public String getActiveTokenType() {
    return tokens.get(activeToken).getType();
  }
  /**
   * @throws LexicalException If no Grammar patterns match. 
   */
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
    Token nextToken = findMatch();
    tokens.add(nextToken);
  }

  private Token findMatch() throws LexicalException {
    if(hitEndOfInput()) {
      return new Token(END_TOKEN_TYPE, "");
    }  else {
      return foundMatch();
    }
  }

  private Token foundMatch() throws LexicalException {
    Matcher matcher = grammar.getMatcher(toMatch);
    Token longest = null;
    Token current = null;
    if (matcher.find()) {
      for(int i = 0; i < grammar.getNumberOfTokenTypes(); i++) {
        TokenType type = grammar.getTokenType(i);
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
      throw new LexicalException("Grammar didn't match.");
    }
    return longest;
  }
  
  private boolean hitEndOfInput () {
    return grammar.getMatcher(toMatch).hitEnd() || toMatch.length() == 0;
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