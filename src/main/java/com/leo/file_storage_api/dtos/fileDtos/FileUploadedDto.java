package com.leo.file_storage_api.dtos.fileDtos;

import com.leo.file_storage_api.models.file.File;

public record FileUploadedDto(
    String username,
    int fileSize
) {
  public static FileUploadedDto from(File file) {
    return new FileUploadedDto(file.getMap().getUser().getName(), file.getContent().length());
  }
}
