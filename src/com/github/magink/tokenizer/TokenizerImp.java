package com.github.magink.tokenizer;

import java.util.stream.Stream;

/**
 * Tokenizer analyses a String input and classifies it according to the rules of a Grammar input. 
 * The user can either request the active token, step forwards or backwards to 
 * get the next or previous token. 
 * When user has reached the end of the String input a special END token is returned.
 */

public class TokenizerImp implements Tokenizer {

  private String toMatch = "";
  private int activeToken = 0;
  private Grammar grammar; 
  private Tokens tokens = new Tokens();

  /**
   * @param grammar Object that contain token types and regex rules.
   * @param toMatch The string to tokenize. 
   * @throws LexicalException if grammar can't find a token.
   */
  public TokenizerImp(Grammar grammar, String toMatch) throws LexicalException {
    this.toMatch = toMatch;
    this.grammar = grammar;
    findNextToken(); 
  }
  /**
   * @return A Token object that contain both the value and the type.  
   */
  public TokenImp getActiveToken() {
    return tokens.getToken(activeToken);
  }
  public String getActiveTokenValue() {
    return tokens.getToken(activeToken).getValue();
  }
  public String getActiveTokenType() {
    return tokens.getToken(activeToken).getType();
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
    TokenImp nextToken = grammar.findMatch(toMatch);
    tokens.addToken(nextToken);
  }

  private boolean hasEndToken() {
    return tokens
      .getToken(tokens.size() -1)
      .getType()
      .equals(grammar.getEndTokenType());
  }
  
  @Override
  public Stream<TokenImp> getTokenStream() {
    return tokens.getStream();
  }
}