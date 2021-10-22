# Tokenizer

A Tokenizer/Lexer written in Java.

The Tokenizer takes a input String from the user and splits it into subsections called Tokens, one-by-one, left to right, and classifies them based on a Grammar. Tokens have a type defined by regex patterns and a value, the subsection of the input string.

---

## How to add to your project

Download the release .jar and add it as a reference library to your build tools or IDE.

## How to use

Create an empty Grammar object, add your token type and name. You can add many token types by repeatably calling

- `.addType(String yourRegex, String tokenTypeName)`.

Once all grammar is added, create a tokenizer and send in the grammar and your string to analyse.

- `new Tokenizer(Grammar myGrammar, String toAnalyse)`

You can get the active/selected token as a String from the tokenizer by calling either

  1. `.getActiveToken()` to get a String representation of both the token value and type.
  2. `.getActiveTokenType()` to only get the token type.
  3. `.getActiveTokenValue()` to only get the token value.

Step to next token by calling

- `.nextToken()`

If no tokens are found a LexicalException is thrown.

Step back to previous token by calling.

- `.previousToken()`

If at the end of input, an end token is returned. By default this token type is simply called "END".
The user also has the option to set a custom end token by using the overloaded constructor when creating a grammar object.

- `Grammar grammar = new Grammar("STOP")`

## Examples

### Example 1: Creating a Grammar from two regexes with custom end token

```java
String regexWord = "[[a-zA-Z]|åäöÅÄÖ]+";
String regexDot = "\\.+";
Grammar myGrammar = new Grammar("NO_MORE_TOKENS")
myGrammar.addType(regexWord, "WORD");
myGrammar.addType(regexDot, "DOT");
```

### Example 2: Get active token

```java
String input = "We want to tokenize this text.";
String activeToken;
try {
  Tokenizer tokenizer = new Tokenizer(grammar, input);
  
  activeToken = tokenizer.getActiveToken(); 
  System.out.println(activeToken) 
  // Output: "Type: WORD   Value: We"
  
  String activeTokenValue = tokenizer.getActiveTokenValue();
  System.out.println(activeTokenValue) 
  // Output: "We"
  
  String activeTokenType = tokenizer.getActiveTokenType();
  System.out.println(activeTokenValue) 
} catch(LexicalException le) {
  // Handle the exception
}
// Output: "WORD"*
```

### Example 3: Get next token

```java
String activeTokenValue;
try {
  tokenizer.nextToken();
  activeTokenValue = tokenizer.getActiveTokenValue();
} catch (LexicalException le){  
  // Handle exception
}

System.out.println(activeTokenValue) 
// Output: "want"
```

### Example 4: Get previous token

```java
String activeTokenValue;
try {
  tokenizer.previousToken()
  activeTokenValue = tokenizer.getActiveTokenValue();

} catch(LexicalException le) {
  // handle exception
}
System.out.println(activeTokenValue) 
// Output: "We"
```

---

## Tests

If you want to run the tests you need to add junit for your build tools or download `junit-platform-console-standalone-1.8.0.jar` or later and add it to your Reference Libraries.

## Known bugs

The regex anchor beginning "^" can cause problems. It's works without it.
