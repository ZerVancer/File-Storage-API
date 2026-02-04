package com.leo.file_storage_api.controllers.fileController;

import com.leo.file_storage_api.dtos.fileDtos.FileDeletedDto;
import com.leo.file_storage_api.dtos.fileDtos.FileUploadedDto;
import com.leo.file_storage_api.models.file.File;
import com.leo.file_storage_api.services.fileService.FileService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("file")
@AllArgsConstructor
public class FileController {
  private FileService fileService;

  @PostMapping("/{userID}")
  public ResponseEntity<FileUploadedDto> uploadFile(
      @PathVariable UUID userID,
      @RequestHeader(value = "mapID", required = false) UUID mapID,
      @RequestBody String content) {

    File file = fileService.saveFile(userID, mapID, content);

    return ResponseEntity.status(HttpStatus.CREATED).body(FileUploadedDto.from(file));
  }

  @DeleteMapping("/{fileID}")
  public ResponseEntity<FileDeletedDto> deletFile(
      @PathVariable UUID fileID
  ) {
    File file = fileService.deleteFile(fileID);

    return ResponseEntity.status(HttpStatus.ACCEPTED).body(FileDeletedDto.from(file));
  }

  @GetMapping
  public ResponseEntity<List<File>> getAll() {
    return ResponseEntity.ok(fileService.getAll());
  }
}
