package com.grupp5.file_storage_api.dtos.fileDtos;

import com.grupp5.file_storage_api.models.file.File;
import com.grupp5.file_storage_api.models.user.User;

public record FileUploadedDto(
    User user,
    int fileSize
) {
  public static FileUploadedDto from(File file) {
    return new FileUploadedDto(file.getMap().getUser(), file.getContent().length());
  }
}
