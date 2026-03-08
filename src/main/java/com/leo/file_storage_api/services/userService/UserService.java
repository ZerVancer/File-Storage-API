package com.leo.file_storage_api.services.userService;

import com.leo.file_storage_api.exceptions.userExceptions.UserCreateException;
import com.leo.file_storage_api.exceptions.userExceptions.UserNotFoundException;
import com.leo.file_storage_api.models.map.Map;
import com.leo.file_storage_api.models.user.User;
import com.leo.file_storage_api.repositories.mapRepository.MapRepository;
import com.leo.file_storage_api.repositories.userRepository.UserRepository;
import com.leo.file_storage_api.security.JwtService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import jakarta.security.auth.message.AuthException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
  private final UserRepository userRepository;
  private final MapRepository mapRepository;
  private final JwtService jwtService;

  public User createUser(String eMail, String password) {
    if (userRepository.findByeMail(eMail).isPresent()) {
      throw new UserCreateException();
    }

    User user = new User(eMail, password);
    userRepository.save(user);
    mapRepository.save(new Map(null, user));

    return user;
  }

  public User createOidcUser(String eMail, String oidcId, String oidcProvider) throws UserCreateException {
    if (userRepository.findByeMail(eMail).isPresent()) {
      throw new UserCreateException();
    }

    var user = new User(eMail, null);
    user.setOidcId(oidcId);
    user.setOidcProvider(oidcProvider);

    user = userRepository.save(user);
    System.out.println("OIDC User "+user.getUserID()+" with name "+user.getEMail()+" created.");

    return user;
  }

  public String authenticateUser(String eMail, String password) throws AuthException {
    User user = userRepository.findByeMail(eMail).orElseThrow(UserNotFoundException::new);

    if (!Objects.equals(user.getPassword(), password)) {
      throw new AuthException();
    }

    return jwtService.generateToken(user.getUserID());
  }

  public User getUser(UUID userID) {
    return userRepository.findByUserID(userID).orElseThrow(UserNotFoundException::new);
  }

  public Optional<User> getUserByOidc(String oidID, String oidcProvider) {
    return userRepository.findByOidcIdAndOidcProvider(oidID, oidcProvider);
  }

  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  @Override
  @NonNull
  public UserDetails loadUserByUsername(@NonNull String eMail) throws UsernameNotFoundException {
    return userRepository.findByeMail(eMail).orElseThrow(UserNotFoundException::new);
  }
}
