package com.github.magink.tokenizer;

import java.util.ArrayList;
import java.util.regex.Matcher;

/**
 * Tokenizer analyses sections of a String input  and
 * classifies it according to the rules of a Grammar input. 
 * The user can either request the active token, step forwards or backwards to 
 * get the next or previous token. When user has reached the end of the String input 
 * a special END token is returned.
 */

public class Tokenizer {
  private String toMatch = "";
  private int activeToken = 0;
  private Grammar grammar; 
  private ArrayList<Token> tokens = new ArrayList<>();

  public Tokenizer(Grammar grammar, String input) throws LexicalException {
    this.toMatch = input;
    this.grammar = grammar;
    findNextToken(); 
  }
  public String getActiveToken() {
    System.out.println("active token is " + activeToken);
    return tokens.get(activeToken).toString();
  }
  public void nextToken() throws LexicalException {
    if (activeToken < tokens.size()) { // This doesn't make sense logically
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
    if (hasManyTokenTypes()) {
      nextToken = findLongestMatch();
    } else {
      TokenType type = grammar.getTokenType(0);
      nextToken = findMatch(type);
    }
    if (nextToken == null) {
      throw new LexicalException("Grammar didn't match input.");
    }
    tokens.add(nextToken);
  }

  /**
   * Matches against many TokenTypes for the longest value. 
   * @return A matched Token with type and value.  
   */
  private Token findLongestMatch() {
    Token longest = new Token("", "");
    Token current = new Token("", "");
    for(int i = 0; i < grammar.getNumberOfTokenTypes(); i++) {
      TokenType type = grammar.getTokenType(i);
      current = findMatch(type);
      if (current.getValue().length() > longest.getValue().length()) {
        longest = current;
      }
    }
    return longest;
  }

  /**
   * Matches against a single TokenType. 
   * @param type The token type to match against, name of type and pattern. 
   * @return a matched Token with type and value. 
   * Returns a special END token if no more matched tokens are found.
   */
  private Token findMatch(TokenType type) {
    Matcher matcher = type.getMatcher(toMatch);
    Token token = null;
    if(matcher.hitEnd()) {
      token = new Token("END", "");
    } 
    else if (matcher.find()) {
      String matchedValue = 
        toMatch
        .substring(
          matcher.start(),
          matcher.end());
      token = new Token(type.getName(), matchedValue);
    }
    return token;
  }
  private boolean hasManyTokenTypes () {
    return grammar.getNumberOfTokenTypes() > 1;
  }
}