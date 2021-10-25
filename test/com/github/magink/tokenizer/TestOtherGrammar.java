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
  private static final String FLOAT = "FLOAT";
  private static final String INTEGER = "INTEGER";
  String regexWord = "[[a-zA-Z]|åäöÅÄÖ]+";
  String regexExclamation = "[!]"; 
  String regexComma = "[,]";
  String regexDot = "[.]";
  String regexFloat = "[0-9]+(\\.([0-9])+)";
  String regexInteger = "[0-9]+";
  
  Grammar grammar;

  @BeforeEach
  void prepareGrammar() {
    grammar = new Grammar();
  }
  @Test
  @DisplayName("TC 17: Should return last token when last token is hit.")
  void shouldGetLastTokenWhenHitEnd () throws LexicalException {
    String input = "Ta en öl Dino!!";
    grammar.addType(regexWord, WORD);
    grammar.addType(regexExclamation, EXCLAMATION);
    TokenizerImp tokenizer = new TokenizerImp(grammar, input);
    for(int i = 0; i < 5; i++) {
      tokenizer.nextToken();
    }
    assertEquals(EXCLAMATION, tokenizer.getActiveTokenType());
    assertEquals("!", tokenizer.getActiveTokenValue());
  }
  @Test
  @DisplayName("TC 18: Should return end-token when stepping past last token")
  void shouldGetEndTokenWhenSteppingPastLastToken () throws LexicalException{
    String input = "Doors and corners, kid";
    grammar.addType(regexWord, WORD);
    grammar.addType(regexExclamation, EXCLAMATION);
    grammar.addType(regexComma, COMMA);
    TokenizerImp tokenizer = new TokenizerImp(grammar, input);
    for(int i = 0; i < 5 ; i++) {
      tokenizer.nextToken();
    }
    assertEquals("END", tokenizer.getActiveTokenType());
    assertEquals("", tokenizer.getActiveTokenValue());
  }
  @Test
  @DisplayName("TC 19: Should return first token when stepping back from first token")
  void shouldReturnFirstTokenWhenSteppingBackFromFirstToken () throws LexicalException {
    String input = "No laws in Ceres. Just cops.";
    grammar.addType(regexWord, WORD);
    grammar.addType(regexDot, DOT);
    TokenizerImp tokenizer = new TokenizerImp(grammar, input);
    tokenizer.previousToken();
    tokenizer.previousToken();
    assertEquals(WORD, tokenizer.getActiveTokenType());
    assertEquals("No", tokenizer.getActiveTokenValue());
  }
  @Test
  @DisplayName("TC 20: Should hit both input \"borders\" and not step past them when repeatably stepping through the tokens in both directions") 
  void ShouldHitEndTokenThenHitFirstToken () throws LexicalException {
    String input = "Go into a room too fast, kid, the room eats you";
    grammar.addType(regexWord, WORD);
    grammar.addType(regexDot, DOT);
    grammar.addType(regexComma, COMMA);
    TokenizerImp tokenizer = new TokenizerImp(grammar, input);
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
  @DisplayName("TC 21: Should apply maximal munch rules and return longest token when several types can match") 
  void maximalMunchWhenManyMatches() throws LexicalException {
    String input = "3.14 5";
    grammar.addType(regexFloat, FLOAT);
    grammar.addType(regexInteger, INTEGER);
    TokenizerImp tokenizer = new TokenizerImp(grammar, input);
    assertEquals("3.14", tokenizer.getActiveTokenValue());
    assertEquals(FLOAT, tokenizer.getActiveTokenType());
    tokenizer.nextToken();
    assertEquals("5", tokenizer.getActiveTokenValue());
    assertEquals(INTEGER, tokenizer.getActiveTokenType());
  }
  @Test
  @DisplayName("TC 22: Should not be able to add duplicate patterns")
  void noDuplicatePatternsWhenTwoPatternsAdded () {
    grammar.addType(regexWord, "WORD");
    grammar.addType(regexWord, "WORD");
    assertEquals(1, grammar.getNumberOfTokenTypes());
  }
  
  
}
