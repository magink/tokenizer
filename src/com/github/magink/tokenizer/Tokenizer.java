package com.github.magink.tokenizer;

import java.util.stream.Stream;

public interface Tokenizer {

  /**
   * @return return a stream of Tokens
   */
  public Stream<TokenImp> getTokenStream();
}
