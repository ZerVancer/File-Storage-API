package com.leo.file_storage_api.exceptions.mapExceptions;

public class MapNotFoundException extends RuntimeException {
  public MapNotFoundException() {
    super("Map not found!");
  }
}
