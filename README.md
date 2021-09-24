# Tokenizer

A Tokenizer/Lexer written in Java.

The tokenizer takes a String from the user and splits it into subsections called Tokens based on a Grammar. Tokens have a type defined by regex patterns sent in by the user.

---

## How to use

Create an empty Grammar object and add your token types names and their regexes

- `.addType(String yourRegex, String tokenTypeName)`.

You can get the active/selected token as a String by calling either

  1. `.getActiveToken()` to get a String representation of both the token value and type.
  2. `.getActiveTokenType()` to only get the token type.
  3. `.getActiveTokenValue()` to only get the token value.

Step to next token by calling

- `.nextToken()`

If no tokens are found an exception is thrown. If at end of input an END token is returned.

Step back to previous token by calling.

- `.previousToken()`

## Examples

### Example 1: creating a Grammar from two regexes

```java
String regexWord = "[[a-zA-Z]|åäöÅÄÖ]+";
String regexDot = "\\.+";
Grammar myGrammar = new Grammar()
myGrammar.addType(regexWord, "WORD");
myGrammar.addType(regexDot, "DOT");
```

### Example 2: get active token

```java
String input = "We want to tokenize this text."
Tokenizer tokenizer = new Tokenizer(grammar, input);

String activeToken = tokenizer.getActiveToken(); 
System.out.println(activeToken) 
// Output: "Type: WORD   Value: We"

String activeTokenValue = tokenizer.getActiveTokenValue();
System.out.println(activeTokenValue) 
// Output: "We"

String activeTokenType = tokenizer.getActiveTokenType();
System.out.println(activeTokenValue) 
// Output: "WORD"*
```

### Example 3: get next token

```java
tokenizer.nextToken()

String activeTokenValue = tokenizer.getActiveTokenValue();
System.out.println(activeTokenValue) 
// Output: "want"
```

### Example 4: get previous token

```java
tokenizer.previousToken()

String activeTokenValue = tokenizer.getActiveTokenValue();
System.out.println(activeTokenValue) 
// Output: "We"
```

## Tests

If you want to run the tests you need to add junit for your build tools or download `junit-platform-console-standalone-1.8.0.jar` or later and add it to your Reference Libraries.
