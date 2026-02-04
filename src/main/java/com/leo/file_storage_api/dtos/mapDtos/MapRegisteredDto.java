package com.leo.file_storage_api.dtos.mapDtos;

import com.leo.file_storage_api.models.map.Map;

import java.util.UUID;

public record MapRegisteredDto(
    UUID mapID,
    String location,
    UUID userID
) {
  public static MapRegisteredDto from(Map map) {
    return new MapRegisteredDto(map.getMapID(), map.getLocation(), map.getUser().getUserID());
  }
}
