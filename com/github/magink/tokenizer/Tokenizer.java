package com.github.magink.tokenizer;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tokenizer {
  private String input = "";
  private int activeToken = 0;
  private ArrayList<Pattern> patterns = new ArrayList<>();
  private ArrayList<String> tokens = new ArrayList<>();

  public Tokenizer(String regex, String input) {
    this.input = input;
    Pattern pattern = Pattern.compile(regex);
    patterns.add(pattern);
    // System.out.println("Pattern is " + patterns.toString());
    findToken();
  }
  // public Tokenizer(String[] regexes, String input) {
  //   this.input = input;
  //   for(String regex : regexes) {
  //     Pattern pattern = Pattern.compile(regex);
  //     patterns.add(pattern);
  //   }
  //   System.out.println(patterns.toString());
  // }
  // public String getToken() {
  //   return tokens.get(activeToken);
  // }
  // public void nextToken() {
  //   activeToken++;
  // }
  private void findToken () {
    for(int i = 0; i < patterns.size(); i++) {
      Matcher matcher = patterns
                        .get(i)
                        .matcher(input);
      // System.out.println(matcher.toString());
      while(matcher.find()) {
        String token = input
                      .substring(
                          matcher.start(),
                          matcher.end());
        System.out.println("token value: " + token);
      }
      // System.out.println(matcher.toString());
      tokens.add("a");
    }
  }
}