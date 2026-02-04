package com.leo.file_storage_api.models.file;

import com.leo.file_storage_api.models.map.Map;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "files")
@Getter
@Setter
@AllArgsConstructor
public class File {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID fileID;

  @ManyToOne
  @JoinColumn(nullable = false)
  private Map map;

  @Column
  private String name;

  @Column
  private String content;

  public File() {}

  public File(Map map, String name, String content) {
    this.map = map;
    this.name = name;
    this.content = content;
  }
}
