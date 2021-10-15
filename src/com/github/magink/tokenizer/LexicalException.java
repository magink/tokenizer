package com.github.magink.tokenizer;
/**
 * This exception occur when a match couldn't be found for a particular token type.  
 */
public class LexicalException extends RuntimeException {
  public LexicalException(String message) {
    super(message);
  }
}