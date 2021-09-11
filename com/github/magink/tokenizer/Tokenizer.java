package com.github.magink.tokenizer;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Tokenizer classifies sections of a user supplied String from the user and
 * classifies it according to the rules of the user supplied 
 * Grammar in the form of regex(es)
 * 
 * The user sends in one or more TokenType in an array and the String 
 * to be tokenized.  
 *   
 */

public class Tokenizer {
  private String input = "";
  private int activeToken = 0;
  private Grammar grammar; 
  private ArrayList<Token> tokens = new ArrayList<>();

  public Tokenizer(Grammar grammar, String input) {
    this.input = input;
    this.grammar = grammar;
    System.out.println(grammar.toString());
    findToken();
  }
  public Token getToken() {
    return tokens.get(activeToken);
  }
  public void nextToken() {
    activeToken++;
  }
  private void hasNextToken(){

  }
  private void findToken () {

    for(int i = 0; i < grammar.NumberOfTokenTypes(); i++) {
      Matcher matcher = grammar
                        .getTokenTypes()
                        .get(i)
                        .getPattern()
                        .matcher(input); 
      while(matcher.find()) {
        String value = input
                      .substring(
                          matcher.start(),
                          matcher.end());
        Token token = new Token("WORD", value);
        System.out.println(token.toString());
        tokens.add(token);
      }
    }
  }
}