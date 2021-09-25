package com.github.magink.tokenizer;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TestWordAndDotGrammar {
  String regexWord = "[[a-zA-Z]|åäöÅÄÖ]+";
  String regexDot = "\\.+";

  Grammar grammar;
  
  @BeforeEach
  void prepareGrammar() {
    grammar = new Grammar();
    grammar.addType(regexWord, "WORD");
    grammar.addType(regexDot, "DOT");
  }
  
  @Test
  @DisplayName("TC1")
  void getActiveToken() throws LexicalException {
    String input = "a";
    Tokenizer tokenizer = new Tokenizer(grammar, input);
    assertEquals("a", tokenizer.getActiveTokenValue());
    assertEquals("WORD", tokenizer.getActiveTokenType());
  }
  @Test
  @DisplayName("TC2")
  void nextToken() throws LexicalException {
    String input = "a aa";
    Tokenizer tokenizer = new Tokenizer(grammar, input);
    tokenizer.nextToken();
    assertEquals("WORD", tokenizer.getActiveTokenType());
    assertEquals("aa", tokenizer.getActiveTokenValue());
  }
  @Test
  @DisplayName("TC3")
  void getSecondTokenType() throws LexicalException {
    String input = "a.b";
    Tokenizer tokenizer = new Tokenizer(grammar, input);
    tokenizer.nextToken();
    assertEquals("DOT", tokenizer.getActiveTokenType());
    assertEquals(".", tokenizer.getActiveTokenValue());
  }
  @Test
  @DisplayName("TC4")
  void steppingTwice() throws LexicalException {
    String input = "a.b";
    Tokenizer tokenizer = new Tokenizer(grammar, input);
    tokenizer.nextToken();
    tokenizer.nextToken();
    assertEquals("WORD", tokenizer.getActiveTokenType());
    assertEquals("b", tokenizer.getActiveTokenValue());
  }
   @Test
   @DisplayName("TC5")
   void steppingTwiceWithSpace() throws LexicalException  {
     String input = "aa. b";
     Tokenizer tokenizer = new Tokenizer(grammar, input);
     tokenizer.nextToken();
     tokenizer.nextToken();
     assertEquals("WORD", tokenizer.getActiveTokenType());
     assertEquals("b", tokenizer.getActiveTokenValue());
   }
   @Test
   @DisplayName("TC6")
   void previousToken() throws LexicalException {
    String input = "a .b";
    Tokenizer tokenizer = new Tokenizer(grammar, input);
    tokenizer.nextToken();
    tokenizer.nextToken();
    tokenizer.previousToken();
    assertEquals("DOT", tokenizer.getActiveTokenType());
    assertEquals(".", tokenizer.getActiveTokenValue());
   }
   @Test 
   @DisplayName("TC7")
   void emptyStringReturnsEndToken() throws LexicalException {
    String input = "";
    Tokenizer tokenizer = new Tokenizer(grammar, input);
    assertEquals("END", tokenizer.getActiveTokenType());
    assertEquals("", tokenizer.getActiveTokenValue());
   }
   @Test
   @DisplayName("TC8")
   void whiteSpaceThrowsException() {
    String input = " ";
    assertThrows(LexicalException.class, () -> {
      Tokenizer tokenizer = new Tokenizer(grammar, input);
    });
   }
   @Test 
   @DisplayName("TC9")
   void endTokenOnEnd() throws LexicalException {
    String input = "a";
    Tokenizer tokenizer = new Tokenizer(grammar, input);
    tokenizer.nextToken();
    assertEquals("END", tokenizer.getActiveTokenType());
    assertEquals("", tokenizer.getActiveTokenValue());
   }
   @Test
   @DisplayName("TC10")
   void previousTokenOnFirstReturnsFirst() throws LexicalException {
     String input = "a";
     Tokenizer tokenizer = new Tokenizer(grammar, input);
     tokenizer.previousToken();
     assertEquals("a", tokenizer.getActiveTokenValue());
     assertEquals("WORD", tokenizer.getActiveTokenType());
   }
   @Test
   @DisplayName("TC11")
   void exceptionOnMissingTokenType() {
     String input = "3";
     assertThrows(LexicalException.class, () -> {
      Tokenizer tokenizer = new Tokenizer(grammar, input);
      assertEquals("a", tokenizer.getActiveTokenValue());
    });
   }
}