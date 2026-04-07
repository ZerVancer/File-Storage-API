package com.leo.file_storage_api.exceptions.authExceptions;

public class InvalidIdException extends RuntimeException {
  public InvalidIdException() {
    super("Invalid Id used for user");
  }
}
