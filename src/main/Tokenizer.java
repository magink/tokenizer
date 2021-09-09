package src.main;

public class Tokenizer {
  private static final String WORD_TOKEN = "/^[0-9]+/ ";
  private int currentToken = 0;
  private String input = "";
  Tokenizer(String input) {
    this.input = input;
  }
  public int getCurrentToken() {
    System.out.println("currentToken is" + 0);
    return currentToken;
  }
}