package com.github.magink.tokenizer;

import java.util.ArrayList;

/**
 * Tokenizer analyses a String input and classifies it according to the rules of a Grammar input. 
 * The user can either request the active token, step forwards or backwards to 
 * get the next or previous token. 
 * When user has reached the end of the String input a special END token is returned.
 */

public class Tokenizer {

  private String toMatch = "";
  private int activeToken = 0;
  private Grammar grammar; 
  private ArrayList<Token> tokens = new ArrayList<>();

  /**
   * @param grammar Object that contain token types and regex rules.
   * @param toMatch The string to tokenize. 
   * @throws LexicalException if grammar can't find a token.
   */
  public Tokenizer(Grammar grammar, String toMatch) throws LexicalException {
    this.toMatch = toMatch;
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

  private void findNextToken () {
    Token nextToken = grammar.findMatch(toMatch);
    tokens.add(nextToken);
  }



  private boolean hasEndToken() {
    return tokens
      .get(tokens.size() -1)
      .getType()
      .equals(grammar.getEndTokenType());
  }
}