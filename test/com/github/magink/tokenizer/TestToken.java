package com.github.magink.tokenizer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class TestToken {
  @Test
  @DisplayName("Testing value")
  void testConstructor() {
    Token token = new Token("WORD", "Hallu");
    assertEquals(token.getType(), "WORD");
    assertEquals(token.getValue(), "Hallu");
  }
}
