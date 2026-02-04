package com.grupp5.file_storage_api.exceptions.userExceptions;

public class EMailAlreadyExistsException extends RuntimeException {
  public EMailAlreadyExistsException() {
    super("Email already exists!");
  }
}
