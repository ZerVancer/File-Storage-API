package com.leo.file_storage_api.exceptions.mapExceptions;

public class IncorrectUserForMapException extends RuntimeException {
  public IncorrectUserForMapException() {
    super("User called doesn't match User present in Map");
  }
}
