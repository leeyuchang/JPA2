package com.jpa1.jpa1.domain;

public class NotEnoughStockException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public NotEnoughStockException(String message) {
    super(message);
  }

}
