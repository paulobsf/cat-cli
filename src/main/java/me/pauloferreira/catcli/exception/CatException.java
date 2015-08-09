package me.pauloferreira.catcli.exception;

public class CatException extends RuntimeException {

  public CatException(String message, Throwable cause) {
    super(message, cause);
  }

  public CatException(String message) {
    super(message);
  }
}
