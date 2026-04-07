package com.leo.file_storage_api.exceptions.authExceptions;

public class InvalidCredentialsException extends RuntimeException {
  public InvalidCredentialsException() {
    super("Invalid e-mail or password!");
  }
}
