package com.leo.file_storage_api.models.map;

import com.leo.file_storage_api.models.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "maps")
@Getter
@Setter
@AllArgsConstructor
public class Map {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID mapID;

  @Column
  private String name;

  @ManyToOne
  @JoinColumn(nullable = false)
  private User user;

  public Map() {}

  public Map(String name, User user) {
    this.name = name;
    this.user = user;
  }
}
