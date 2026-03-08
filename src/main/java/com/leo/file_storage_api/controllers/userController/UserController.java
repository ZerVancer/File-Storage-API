package com.leo.file_storage_api.controllers.userController;

import com.leo.file_storage_api.dtos.authDtos.InvalidCredentialsException;
import com.leo.file_storage_api.dtos.mapDtos.MapGetDto;
import com.leo.file_storage_api.dtos.userDtos.UserGetDto;
import com.leo.file_storage_api.dtos.userDtos.UserRegisteredDto;
import com.leo.file_storage_api.models.map.Map;
import com.leo.file_storage_api.models.user.User;
import com.leo.file_storage_api.requests.userRequests.CreateUserRequest;
import com.leo.file_storage_api.requests.userRequests.LoginUserRequest;
import com.leo.file_storage_api.services.userService.UserService;
import jakarta.security.auth.message.AuthException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
  private UserService userService;

  @PostMapping("/register")
  public ResponseEntity<UserRegisteredDto> createUser(
      @RequestBody CreateUserRequest request
  ) {
    User user = userService.createUser(request.eMail(), request.password());

    return ResponseEntity.status(HttpStatus.CREATED).body(UserRegisteredDto.from(user));
  }

  @PostMapping("/login")
  public ResponseEntity<?> loginUser(
      @RequestBody LoginUserRequest request
  ) {
    try {
      var token = userService.authenticateUser(request.eMail(), request.password());
      return ResponseEntity.ok(token);
    } catch (AuthException e) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new InvalidCredentialsException());
    }
  }

//  These are Admin commands and can be constrained with an Admin role when implemented
//  @GetMapping("/{userID}")
//  public ResponseEntity<UserGetDto> getUserById(
//      @PathVariable UUID userID
//  ) {
//    return ResponseEntity.ok(UserGetDto.from(userService.getUser(userID)));
//  }
//
//  @GetMapping("/get-all")
//  public ResponseEntity<List<UserGetDto>> getAllUsers() {
//    List<UserGetDto> list = new ArrayList<>();
//
//    for (User user : userService.getAllUsers()) {
//      list.add(UserGetDto.from(user));
//    }
//
//    return ResponseEntity.ok(list);
//  }
}
