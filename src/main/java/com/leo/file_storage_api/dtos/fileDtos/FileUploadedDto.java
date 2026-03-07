package com.leo.file_storage_api.dtos.fileDtos;

import com.leo.file_storage_api.models.file.File;

import java.util.UUID;

public record FileUploadedDto(
    UUID userID,
    int fileSize
) {
  public static FileUploadedDto from(File file) {
    return new FileUploadedDto(file.getMap().getUser().getUserID(), file.getContent().length());
  }
}
