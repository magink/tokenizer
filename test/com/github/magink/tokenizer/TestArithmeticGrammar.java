package com.github.magink.tokenizer;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestArithmeticGrammar {
  String regexNumber = "[0-9]+(\\.([0-9])+)?";
  String regexAdd= "[+]";
  String regexMul= "[*]";
  String regexHash = "[#]";

  Grammar grammar;

  @BeforeEach
  void prepareGrammar() {
    grammar = new Grammar();
    grammar.addType(regexNumber, "NUMBER");
    grammar.addType(regexAdd, "ADD");
    grammar.addType(regexMul, "MUL");
  }
  @Test
  @DisplayName("TC 12")
  void wholeNumber() throws LexicalException {
    String input = "3";
    Tokenizer tokenizer = new Tokenizer(grammar, input);
    assertEquals("3", tokenizer.getActiveTokenValue());
    assertEquals("NUMBER", tokenizer.getActiveTokenType());
  }
  @Test
  @DisplayName("TC 13")
  void decimalNumber() throws LexicalException {
    String input = "3.14";
    Tokenizer tokenizer = new Tokenizer(grammar, input);
    assertEquals("3.14", tokenizer.getActiveTokenValue());
    assertEquals("NUMBER", tokenizer.getActiveTokenType());
  }
  @Test
  @DisplayName("TC 14")
  void multiplication() throws LexicalException {
    String input = "3 + 54 * 4";
    Tokenizer tokenizer = new Tokenizer(grammar, input);
    tokenizer.nextToken();
    tokenizer.nextToken();
    tokenizer.nextToken();
    assertEquals("*", tokenizer.getActiveTokenValue());
    assertEquals("MUL", tokenizer.getActiveTokenType());
  }
  @Test
  @DisplayName("TC 15")
  void invalidGrammar() {
    String input = "3+5 # 4";
    assertThrows(LexicalException.class, () -> {
      Tokenizer tokenizer = new Tokenizer(grammar, input);
      tokenizer.nextToken();
      tokenizer.nextToken();
      tokenizer.nextToken();
      tokenizer.getActiveToken();
    });
  }
  @Test 
  @DisplayName("TC 16")
  void steppingThrice() throws LexicalException {
    String input = "3.0+54.1     + 4.2";
    Tokenizer tokenizer = new Tokenizer(grammar, input);
    tokenizer.nextToken();
    tokenizer.previousToken();
    tokenizer.nextToken();
    tokenizer.nextToken();
    tokenizer.nextToken();
    assertEquals("+", tokenizer.getActiveTokenValue());
    assertEquals("ADD", tokenizer.getActiveTokenType());
  } 
  
}
