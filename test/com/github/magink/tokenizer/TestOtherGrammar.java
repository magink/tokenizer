package com.github.magink.tokenizer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestOtherGrammar {
  private static final String EXCLAMATION = "EXCLAMATION";
  private static final String WORD = "WORD";
  private static final String COMMA = "COMMA";
  private static final String DOT = "DOT";
  String regexWord = "[[a-zA-Z]|åäöÅÄÖ]+";
  String regexExclamation = "[!]"; 
  String regexComma = "[,]";
  String regexDot = "[.]";
  
  Grammar grammar;

  @BeforeEach
  void prepareGrammar() {
    grammar = new Grammar();
  }
  @Test
  @DisplayName("TC 17")
  void lastTokenBeforeEnd () throws LexicalException {
    String input = "Ta en öl Dino!!";
    grammar.addType(regexWord, WORD);
    grammar.addType(regexExclamation, EXCLAMATION);
    Tokenizer tokenizer = new Tokenizer(grammar, input);
    for(int i = 0; i < 5; i++) {
      tokenizer.nextToken();
    }
    assertEquals(EXCLAMATION, tokenizer.getActiveTokenType());
    assertEquals("!", tokenizer.getActiveTokenValue());
  }
  @Test
  @DisplayName("TC 18")
  void endTokenIsReturnedAtEnd () throws LexicalException{
    String input = "Doors and corners, kid";
    grammar.addType(regexWord, WORD);
    grammar.addType(regexExclamation, EXCLAMATION);
    grammar.addType(regexComma, COMMA);
    Tokenizer tokenizer = new Tokenizer(grammar, input);
    for(int i = 0; i < 5 ; i++) {
      tokenizer.nextToken();
    }
    assertEquals("END", tokenizer.getActiveTokenType());
    assertEquals("", tokenizer.getActiveTokenValue());
  }
  @Test
  @DisplayName("TC 19")
  void noPreviousTokenOnFirstToken () throws LexicalException {
    String input = "No laws in Ceres. Just cops.";
    grammar.addType(regexWord, WORD);
    grammar.addType(regexDot, DOT);
    Tokenizer tokenizer = new Tokenizer(grammar, input);
    tokenizer.previousToken();
    tokenizer.previousToken();
    assertEquals(WORD, tokenizer.getActiveTokenType());
    assertEquals("No", tokenizer.getActiveTokenValue());
  }
  @Test
  @DisplayName("TC 20") // This kind of already exist in previous test
  void hitEndTokenThenHitFirstToken () throws LexicalException {
    String input = "Go into a room too fast, kid, the room eats you";
    grammar.addType(regexWord, WORD);
    grammar.addType(regexDot, DOT);
    grammar.addType(regexComma, COMMA);
    Tokenizer tokenizer = new Tokenizer(grammar, input);
    for(int i = 0; i < 15; i++) {
      tokenizer.nextToken();
    }
    assertEquals("END", tokenizer.getActiveTokenType());
    for(int i = 0; i < 15; i++) {
      tokenizer.previousToken();
    }
    assertEquals(WORD, tokenizer.getActiveTokenType());
    assertEquals("Go", tokenizer.getActiveTokenValue());
  }
  @Test
  @DisplayName("TC 21")
  void noDuplicatePatterns () {
    grammar.addType(regexWord, "WORD");
    grammar.addType(regexWord, "WORD");
    assertEquals(1, grammar.getNumberOfTokenTypes());
  }
  
  
}
