package com.grupp5.file_storage_api.services.userService;

import com.grupp5.file_storage_api.exceptions.userExceptions.EMailAlreadyExistsException;
import com.grupp5.file_storage_api.exceptions.userExceptions.UserNotFoundException;
import com.grupp5.file_storage_api.models.map.Map;
import com.grupp5.file_storage_api.models.user.User;
import com.grupp5.file_storage_api.repositories.mapRepository.MapRepository;
import com.grupp5.file_storage_api.repositories.userRepository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService {
  private UserRepository userRepository;
  private MapRepository mapRepository;

  public void createUser(String eMail, String password) {
    if (userRepository.findByEMail(eMail).isPresent()) {
      throw new EMailAlreadyExistsException();
    }

    User user = new User(eMail, password);
    userRepository.save(user);

    mapRepository.save(new Map(null, user));
  }

  public User getUser(UUID userID) {
    return userRepository.findByUserID(userID).orElseThrow(UserNotFoundException::new);
  }

  public List<User> getAllUsers() {
    return userRepository.findAll();
  }
}
