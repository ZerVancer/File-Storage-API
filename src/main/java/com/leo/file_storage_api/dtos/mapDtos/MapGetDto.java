package com.leo.file_storage_api.dtos.mapDtos;

import com.leo.file_storage_api.controllers.fileController.FileController;
import com.leo.file_storage_api.models.map.Map;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Getter
@Setter
public class MapGetDto extends RepresentationModel<MapGetDto> {

  private final UUID mapID;
  private String name;

  public MapGetDto(UUID mapID, String name) {
    this.mapID = mapID;
    this.name = name;
  }

  public static MapGetDto from(Map map) {
    var response = new MapGetDto(map.getMapID(), map.getName());

    response.add(linkTo(
        methodOn(FileController.class).getAllByMapID(map.getMapID(), map.getUser())
    ).withRel("files"));

    return response;
  }
}
