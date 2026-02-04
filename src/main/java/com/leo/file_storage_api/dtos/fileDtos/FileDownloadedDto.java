package com.leo.file_storage_api.dtos.fileDtos;

import com.leo.file_storage_api.models.file.File;

import java.util.UUID;

public record FileDownloadedDto(
    UUID fileID,
    UUID userID,
    String name,
    String content
) {
  public static FileDownloadedDto from(File file) {
    return new FileDownloadedDto(file.getFileID(), file.getMap().getUser().getUserID(), file.getName(), file.getContent());
  }
}
