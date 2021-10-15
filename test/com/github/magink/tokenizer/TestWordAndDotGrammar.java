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
  @DisplayName("TC1: Should get first token when calling getActiveToken() immediately")
  void shouldGetFirstTokenBeforeStepping() throws LexicalException {
    String input = "a";
    Tokenizer tokenizer = new Tokenizer(grammar, input);
    assertEquals("a", tokenizer.getActiveTokenValue());
    assertEquals("WORD", tokenizer.getActiveTokenType());
  }

  @Test
  @DisplayName("TC2: Should get second token when one step forward.")
  void shouldGetNextTokenWhenSteppingForward() throws LexicalException {
    String input = "a aa";
    Tokenizer tokenizer = new Tokenizer(grammar, input);
    tokenizer.nextToken();
    assertEquals("WORD", tokenizer.getActiveTokenType());
    assertEquals("aa", tokenizer.getActiveTokenValue());
  }
  @Test
  @DisplayName("TC3: Should get second token type when many types are entered")
  void getSecondTokenTypeWhenManyTypes() throws LexicalException {
    String input = "a.b";
    Tokenizer tokenizer = new Tokenizer(grammar, input);
    tokenizer.nextToken();
    assertEquals("DOT", tokenizer.getActiveTokenType());
    assertEquals(".", tokenizer.getActiveTokenValue());
  }
  @Test
  @DisplayName("TC4: Should Step twice when calling nextToken() twice")
  void shouldStepTwiceWhenCallingTwice() throws LexicalException {
    String input = "a.b";
    Tokenizer tokenizer = new Tokenizer(grammar, input);
    tokenizer.nextToken();
    tokenizer.nextToken();
    assertEquals("WORD", tokenizer.getActiveTokenType());
    assertEquals("b", tokenizer.getActiveTokenValue());
  }
   @Test
   @DisplayName("TC5: Should step past whitespace when no type for whitespace exist")
   void shouldStepPastSpaceWhenNoType() throws LexicalException  {
     String input = "aa. b";
     Tokenizer tokenizer = new Tokenizer(grammar, input);
     tokenizer.nextToken();
     tokenizer.nextToken();
     assertEquals("WORD", tokenizer.getActiveTokenType());
     assertEquals("b", tokenizer.getActiveTokenValue());
   }
   @Test
   @DisplayName("TC6: Should go back to previous token when calling previousToken")
   void shouldStepBackWhenPreviousToken() throws LexicalException {
    String input = "a .b";
    Tokenizer tokenizer = new Tokenizer(grammar, input);
    tokenizer.nextToken();
    tokenizer.nextToken();
    tokenizer.previousToken();
    assertEquals("DOT", tokenizer.getActiveTokenType());
    assertEquals(".", tokenizer.getActiveTokenValue());
   }
   @Test 
   @DisplayName("TC7: Should return End-token when input string is empty.")
   void shouldReturnEndTokenWhenInputEmpty() throws LexicalException {
    String input = "";
    Tokenizer tokenizer = new Tokenizer(grammar, input);
    assertEquals("END", tokenizer.getActiveTokenType());
    assertEquals("", tokenizer.getActiveTokenValue());
   }
   @Test
   @DisplayName("TC8: Should throw Exception when only whitespace exist in input")
   void shouldThrowExceptionWhenInputIsOnlyWhitespace() {
    String input = " ";
    assertThrows(LexicalException.class, () -> {
      Tokenizer tokenizer = new Tokenizer(grammar, input);
    });
   }
   @Test 
   @DisplayName("TC9: Should return END-token when the entire input has been stepped through")
   void shouldReturnEndTokenWhenReachedEnd() throws LexicalException {
    String input = "a";
    Tokenizer tokenizer = new Tokenizer(grammar, input);
    tokenizer.nextToken();
    assertEquals("END", tokenizer.getActiveTokenType());
    assertEquals("", tokenizer.getActiveTokenValue());
   }
   @Test
   @DisplayName("TC10: Should return first token when stepping back from first token ")
   void shouldReturnFirstTokenWhenSteppingBackFromFirst() throws LexicalException {
     String input = "a";
     Tokenizer tokenizer = new Tokenizer(grammar, input);
     tokenizer.previousToken();
     assertEquals("a", tokenizer.getActiveTokenValue());
     assertEquals("WORD", tokenizer.getActiveTokenType());
   }
   @Test
   @DisplayName("TC11: Should throw exception when a token of a missing type is hit")
   void shouldThrowExceptionWhenMissingTokenType() {
     String input = "3";
     assertThrows(LexicalException.class, () -> {
      Tokenizer tokenizer = new Tokenizer(grammar, input);
      assertEquals("a", tokenizer.getActiveTokenValue());
    });
   }
}