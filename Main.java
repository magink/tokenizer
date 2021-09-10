import com.github.magink.tokenizer.Tokenizer;

public class Main {
  public static void main(String[] args) {
    String regexInteger = "[0-9]+";
    String regexFloat = "[0-9]+\\.[0-9]+";
    // String regexWord = "/[\\w|åäöÅÄÖ]+/g"; 
    String regexWord = "[\\w]+"; 
    String englishWords = "This was the day when... it happened";
    String swedishWords = "Det var en gång en liten hund.";
    String numbers = "1265 432534 234 5";
    String[] manyRegex = new String[] {regexWord, regexInteger};

    Tokenizer tokenizerWord = new Tokenizer(regexInteger, numbers);
  }
}