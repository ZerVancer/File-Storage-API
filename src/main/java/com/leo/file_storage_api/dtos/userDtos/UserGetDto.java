package com.leo.file_storage_api.dtos.userDtos;

import com.leo.file_storage_api.models.user.User;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UserGetDto {

  private final UUID userID;
  private String eMail;

  public UserGetDto(UUID userID, String eMail) {
    this.userID = userID;
    this.eMail = eMail;
  }

  public static UserGetDto from(User user) {
    return new UserGetDto(user.getUserID(), user.getEMail());
  }
}
