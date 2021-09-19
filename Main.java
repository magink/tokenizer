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
    String numbers = "1265 4325.34 234 5";
    String[] manyRegex = new String[] {regexWord, regexInteger};

    Grammar intGrammar = new Grammar();
    // floatGrammar.addType(regexFloat, "FLOAT");
    intGrammar.addType(regexInteger, "INTEGER");

    // Grammar wordGrammar = new Grammar();
    // wordGrammar.addType(regexWord, "WORD");
    // // wordGrammar.addType(regexDot, "DOT");

    try {
      Tokenizer tokenizerWord = new Tokenizer(intGrammar, numbers);
      String activeWord = tokenizerWord.getActiveToken();
      System.out.println(activeWord);
      for (int i = 0; i < 5; i++) {
        System.out.println();
        tokenizerWord.nextToken();
        activeWord = tokenizerWord.getActiveToken();
        System.out.println(activeWord);
      }
      for(int i = 0; i < 3; i++) {
        System.out.println();
        tokenizerWord.previousToken();
        activeWord = tokenizerWord.getActiveToken();
        System.out.println(activeWord);
      }
      for(int i = 0; i < 5; i++) {
        System.out.println();
        tokenizerWord.nextToken();
        activeWord = tokenizerWord.getActiveToken();
        System.out.println(activeWord);
      }
    } catch(Exception e) {
      System.out.println(e);
    }
    
  }
}