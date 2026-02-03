package com.grupp5.file_storage_api.exceptions.userExceptions;

public class UserNotFoundException extends RuntimeException {
  public UserNotFoundException() {
    super("User not found!");
  }
}
