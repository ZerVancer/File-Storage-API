package com.grupp5.file_storage_api.controllers.userController;

import com.grupp5.file_storage_api.models.user.User;
import com.grupp5.file_storage_api.requests.userRequests.CreateUserRequest;
import com.grupp5.file_storage_api.services.userService.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
@AllArgsConstructor
public class UserController {
  private UserService userService;

  @PostMapping
  public ResponseEntity<User> createUser(@RequestBody CreateUserRequest request) {
    userService.createUser(request.eMail(), request.password());

    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @GetMapping
  public ResponseEntity<List<User>> getAllUsers() {
    return ResponseEntity.ok(userService.getAllUsers());
  }
}
