package com.grupp5.file_storage_api.exceptions.fileExceptions;

public class FileNotFoundException extends RuntimeException {
  public FileNotFoundException() {
    super("File not found!");
  }
}
