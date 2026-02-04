package com.leo.file_storage_api.exceptions.userExceptions;

public class EMailAlreadyExistsException extends RuntimeException {
  public EMailAlreadyExistsException() {
    super("Email already exists!");
  }
}
