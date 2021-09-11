import com.github.magink.tokenizer.Tokenizer;
import com.github.magink.tokenizer.Grammar;

public class Main {
  public static void main(String[] args) {
    String regexInteger = "[0-9]+";
    String regexFloat = "[0-9]+\\.[0-9]+";
    String regexSwedish = "/[\\w|åäöÅÄÖ]+/g"; 
    String regexWord = "[\\w]+";
    String regexDot = "^\\.";
    String regexNoDigits =  "[\\w|åäöÅÄÖ]+";
    String englishWords = "This was the day when. it happened";
    String swedishWords = "Det var en gång en liten hund.";
    String numbers = "1265 432534 234 5";
    String[] manyRegex = new String[] {regexWord, regexInteger};


    Grammar wordGrammar = new Grammar();
    wordGrammar.addType(regexWord, "WORD");
    // wordGrammar.addType(regexDot, "DOT");
    Tokenizer tokenizerWord = new Tokenizer(wordGrammar, englishWords);
  }
}