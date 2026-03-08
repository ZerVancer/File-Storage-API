package com.leo.file_storage_api.models.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
public class User implements UserDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID userID;

  @Column(nullable = false)
  private String eMail;

  private String password;

  @Column
  private String oidcId;

  @Column
  private String oidcProvider;

  public User() {}

  public User(String eMail, String password) {
    this.eMail = eMail;
    this.password = password;
  }

  @Override
  @NonNull
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of();
  }

  @Override
  @NonNull
  public String getUsername() {
    return eMail;
  }
}
