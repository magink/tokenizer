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
  @DisplayName("TC 12: Should get number token when input is a whole number")
  void shouldGetNumberTokenWhenInputIsWhole() throws LexicalException {
    String input = "3";
    GrammarTokenizer tokenizer = new GrammarTokenizer(grammar, input);
    assertEquals("3", tokenizer.getActiveTokenValue());
    assertEquals("NUMBER", tokenizer.getActiveTokenType());
  }
  @Test
  @DisplayName("TC 13: Should get number token for decimal when input is decimal")
  void shouldGetNumberTokenWhenInputIsDecimal() throws LexicalException {
    String input = "3.14";
    GrammarTokenizer tokenizer = new GrammarTokenizer(grammar, input);
    assertEquals("3.14", tokenizer.getActiveTokenValue());
    assertEquals("NUMBER", tokenizer.getActiveTokenType());
  }
  @Test
  @DisplayName("TC 14: Should get the multiplication token when multiplication type is added and hit.")
  void shouldGetMultiplicationTokenWhenHitMultiplication() throws LexicalException {
    String input = "3 + 54 * 4";
    GrammarTokenizer tokenizer = new GrammarTokenizer(grammar, input);
    tokenizer.nextToken();
    tokenizer.nextToken();
    tokenizer.nextToken();
    assertEquals("*", tokenizer.getActiveTokenValue());
    assertEquals("MUL", tokenizer.getActiveTokenType());
  }
  @Test
  @DisplayName("TC 15: Should throw exception when no token type exist for current token")
  void shouldThrowExceptionWhenHittingTokenWithNoTypeAdded() {
    String input = "3+5 # 4";
    assertThrows(LexicalException.class, () -> {
      GrammarTokenizer tokenizer = new GrammarTokenizer(grammar, input);
      tokenizer.nextToken();
      tokenizer.nextToken();
      tokenizer.nextToken();
      tokenizer.getActiveToken();
    });
  }
  @Test 
  @DisplayName("TC 16: Should get addition token when stepping three times.")
  void shouldGetAdditionTokenWhenSteppingThrice() throws LexicalException {
    String input = "3.0+54.1     + 4.2";
    GrammarTokenizer tokenizer = new GrammarTokenizer(grammar, input);
    tokenizer.nextToken();
    tokenizer.previousToken();
    tokenizer.nextToken();
    tokenizer.nextToken();
    tokenizer.nextToken();
    assertEquals("+", tokenizer.getActiveTokenValue());
    assertEquals("ADD", tokenizer.getActiveTokenType());
  }
  
}
