package com.grupp5.file_storage_api.controllers.fileController;

import com.grupp5.file_storage_api.dtos.fileDtos.FileUploadedDto;
import com.grupp5.file_storage_api.models.file.File;
import com.grupp5.file_storage_api.services.fileService.FileService;
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

    fileService.saveFile(userID, mapID, content);

    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @GetMapping
  public ResponseEntity<List<File>> getAll() {
    return ResponseEntity.ok(fileService.getAll());
  }
}
