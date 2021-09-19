package com.github.magink.tokenizer;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TestTokenizer {
  String englishWords = "This was the day when. it happened";
  String numbers = "1265 4325.34 234 5";
  String shortNumbers = "3.14 5";

  String regexWord = "[\\w]+";
  String regexDot = "^\\.";
  String regexInteger = "[0-9]+";
  String regexFloat = "[0-9]+\\.[0-9]+";

  Grammar grammar;
  
  @BeforeEach
  void prepare() {
    grammar = new Grammar();
  } 

  @Test
  @DisplayName("Testing one pattern")
  void singleToken() {
    grammar.addType(regexWord, "WORD");
    try {
      Tokenizer tokenizer = new Tokenizer(grammar, englishWords);
      assertEquals(tokenizer.getActiveToken(), "Type: WORD\t Value: This");
    } catch (Exception e) {
      System.out.println(e);
    }
  }
  @Test
  @DisplayName("Testing two patterns") 
  void multiToken() {
    grammar = new Grammar();
    grammar.addType(regexWord, "WORD");
    grammar.addType(regexDot, "DOT");
    
    try {
      Tokenizer tokenizer = new Tokenizer(grammar, englishWords);
      assertEquals(tokenizer.getActiveToken(), "Type: WORD\t Value: This");
    } catch (Exception e) {
      System.out.println(e);
    }
  }
  @Test
  @DisplayName("Test Case 1 - Maximal Munch")
  void maximalMunch() {
    grammar.addType(regexInteger, "INTEGER");
    grammar.addType(regexFloat, "FLOAT");
    try {
      Tokenizer tokenizer = new Tokenizer(grammar, shortNumbers);
      assertEquals(tokenizer.getActiveToken(), "Type: FLOAT\t Value: 3.14");
      tokenizer.nextToken();
      assertEquals(tokenizer.getActiveToken(), "Type: INTEGER\t Value: 5");
    } catch(Exception e) {
      System.out.println(e);
    }
  }
}

