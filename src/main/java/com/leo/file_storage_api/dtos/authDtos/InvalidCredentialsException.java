package com.leo.file_storage_api.dtos.authDtos;

import com.leo.file_storage_api.dtos.userDtos.UserGetDto;
import com.leo.file_storage_api.models.user.User;

import java.util.UUID;

public class InvalidCredentialsException {

  private String message;


  public InvalidCredentialsException() {
    this.message = "Incorrect eMail or password!";
  }

}
