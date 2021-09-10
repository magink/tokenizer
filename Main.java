


public class Main {
  public static void main(String[] args) {
    Tokenizer tokenizer = new Tokenizer("Hejsan lillebror");
    int currentToken = tokenizer.getCurrentToken();
    System.out.println(currentToken);
  }
}