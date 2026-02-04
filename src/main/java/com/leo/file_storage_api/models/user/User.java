package com.leo.file_storage_api.models.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID userID;

  @Column(nullable = false)
  private String eMail;

  @Column(nullable = false)
  private String password;

  public User() {}

  public User(String eMail, String password) {
    this.eMail = eMail;
    this.password = password;
  }
}
