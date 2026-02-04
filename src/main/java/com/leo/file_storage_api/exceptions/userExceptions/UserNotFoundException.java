package com.leo.file_storage_api.exceptions.userExceptions;

public class UserNotFoundException extends RuntimeException {
  public UserNotFoundException() {
    super("User not found!");
  }
}
