package com.leo.file_storage_api.dtos.fileDtos;

import com.leo.file_storage_api.controllers.mapController.MapController;
import com.leo.file_storage_api.models.file.File;
import com.leo.file_storage_api.models.map.Map;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Getter
@Setter
public class FileGetDto extends RepresentationModel<FileGetDto> {

  private final UUID fileID;
  private String name;
  private String content;

  public FileGetDto(UUID fileID, String name, String content) {
    this.fileID = fileID;
    this.name = name;
    this.content = content;
  }

  public static FileGetDto from(File file) {
    var response = new FileGetDto(file.getFileID(), file.getName(), file.getContent());

    response.add(linkTo(
        methodOn(MapController.class).getMapById(file.getMap().getMapID())
    ).withRel("map"));

    return response;
  }
}
