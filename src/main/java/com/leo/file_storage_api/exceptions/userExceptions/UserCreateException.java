package com.leo.file_storage_api.exceptions.userExceptions;

public class UserCreateException extends RuntimeException {
  public UserCreateException() {
    super("Email already exists!");
  }
}
