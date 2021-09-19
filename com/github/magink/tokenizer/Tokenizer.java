package com.github.magink.tokenizer;

import java.util.ArrayList;
import java.util.regex.Matcher;

/**
 * Tokenizer analyses sections of an input String and
 * classifies it according to the rules of the input Grammar. 
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
    if (activeToken < tokens.size()) {
      try {
        findNextToken();
        activeToken++;
      } catch(Exception e) {
        System.out.println(e);
      }
    }
  }
  public void previousToken() {
    if(activeToken > 0) {
      activeToken--;
    }
  }

  private void findNextToken () throws LexicalException {

    Token nextToken;
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
   * @return a matched token that has a type and value
   */
  private Token findMatch(TokenType type) {
    Matcher matcher = type.getMatcher(toMatch);
    if(matcher.find()) {
      String matchedValue = 
        toMatch
        .substring(
          matcher.start(),
          matcher.end());
      Token token = new Token(type.getName(), matchedValue);
      return token;
    } 
    return null;
  }
  private boolean hasManyTokenTypes () {
    return grammar.getNumberOfTokenTypes() > 1;
  }
}