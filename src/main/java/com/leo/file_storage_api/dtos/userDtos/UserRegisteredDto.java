package com.leo.file_storage_api.dtos.userDtos;

import com.leo.file_storage_api.controllers.userController.UserController;
import com.leo.file_storage_api.models.user.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Getter
@Setter
public class UserRegisteredDto extends RepresentationModel<UserRegisteredDto> {

  private final UUID userID;

  public static UserRegisteredDto from(User user) {

    var response = new UserRegisteredDto(
        user.getUserID()
    );

    response.add(linkTo(
        methodOn(UserController.class).getUserById(user.getUserID())
    ).withRel("user"));

    return response;
  }

  public UserRegisteredDto(UUID userID) {
    this.userID = userID;
  }
}
