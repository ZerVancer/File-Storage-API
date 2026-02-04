package com.leo.file_storage_api.dtos.fileDtos;

import com.leo.file_storage_api.models.file.File;

import java.util.UUID;

public record FileDeletedDto(
    UUID fileID,
    UUID mapID,
    UUID userID
) {
  public static FileDeletedDto from(File file) {
    return new FileDeletedDto(file.getFileID(), file.getMap().getMapID(), file.getMap().getUser().getUserID());
  }
}
