package com.leo.file_storage_api.dtos.userDtos;

import com.leo.file_storage_api.models.user.User;

import java.util.UUID;

public record UserRegisteredDto(
    UUID userID,
    String eMail
) {
  public static UserRegisteredDto from(User user) {
    return new UserRegisteredDto(user.getUserID(), user.getEMail());
  }
}
