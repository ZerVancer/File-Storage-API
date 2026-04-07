package com.leo.file_storage_api.dtos.fileDtos;

import com.leo.file_storage_api.controllers.mapController.MapController;
import com.leo.file_storage_api.controllers.userController.UserController;
import com.leo.file_storage_api.models.file.File;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Getter
@Setter
public class FileDeletedDto extends RepresentationModel<FileUploadedDto> {

  private final UUID fileID;

  public FileDeletedDto(UUID fileID) {
    this.fileID = fileID;
  }

  public static FileDeletedDto from(File file) {
    var response = new FileDeletedDto(file.getFileID());

    response.add(linkTo(
        methodOn(MapController.class).getMapById(file.getMap().getMapID(), file.getMap().getUser())
    ).withRel("map"));

    response.add(linkTo(
        methodOn(UserController.class).getUserById(file.getMap().getUser().getUserID())
    ).withRel("user"));

    return response;
  }
}
