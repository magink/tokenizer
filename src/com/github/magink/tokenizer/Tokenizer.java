package com.github.magink.tokenizer;

import java.util.stream.Stream;

public interface Tokenizer {

  /**
   * @return the currently selected token.
   */
  Token getActiveToken();

  /**
   * @return the currently selected token value
   */
  String getActiveTokenValue();

  /**
   * @return the currently selected token type
   */
  String getActiveTokenType();

    /**
   * @return return a stream of Tokens
   */
  Stream<Token> getTokenStream();

  /**
   * Step to the next token
   * @throws LexicalException if no match could be made
   */
  void nextToken() throws LexicalException;

  /**
   * step back to the previous token. 
   */
  void previousToken();
}
