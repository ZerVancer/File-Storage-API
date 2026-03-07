package com.leo.file_storage_api.dtos.fileDtos;

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
public class FileUploadedDto extends RepresentationModel<FileUploadedDto> {
  private final UUID fileID;
  private int fileSize;

  public FileUploadedDto(UUID fileID, int fileSize) {
    this.fileID = fileID;
    this.fileSize = fileSize;
  }

  public static FileUploadedDto from(File file) {
    var response = new FileUploadedDto(file.getFileID(), file.getContent().length());

    response.add(linkTo(
        methodOn(UserController.class).getUserById(file.getMap().getUser().getUserID())
    ).withRel("user"));

    return response;
  }
}
