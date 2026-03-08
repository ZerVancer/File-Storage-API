package com.leo.file_storage_api.controllers.fileController;

import com.leo.file_storage_api.dtos.fileDtos.FileDeletedDto;
import com.leo.file_storage_api.dtos.fileDtos.FileDownloadedDto;
import com.leo.file_storage_api.dtos.fileDtos.FileGetDto;
import com.leo.file_storage_api.dtos.fileDtos.FileUploadedDto;
import com.leo.file_storage_api.models.file.File;
import com.leo.file_storage_api.models.user.User;
import com.leo.file_storage_api.services.fileService.FileService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("file")
@AllArgsConstructor
public class FileController {
  private FileService fileService;

  @PostMapping("/create")
  public ResponseEntity<FileUploadedDto> uploadFile(
      @RequestHeader(value = "mapID", required = false) UUID mapID,
      @RequestParam String name,
      @RequestBody String content,
      @AuthenticationPrincipal User user
  ) {

    File file = fileService.saveFile(user.getUserID(), mapID, name, content);

    return ResponseEntity.status(HttpStatus.CREATED).body(FileUploadedDto.from(file));
  }

  @DeleteMapping("/{fileID}")
  public ResponseEntity<FileDeletedDto> deleteFile(
      @PathVariable UUID fileID
  ) {
    File file = fileService.deleteFile(fileID);

    return ResponseEntity.status(HttpStatus.ACCEPTED).body(FileDeletedDto.from(file));
  }

  @GetMapping("/{fileID}")
  public ResponseEntity<FileDownloadedDto> downloadFile(
      @PathVariable UUID fileID
  ) {
    File file = fileService.getByFileID(fileID);

    return ResponseEntity.ok(FileDownloadedDto.from(file));
  }

  @GetMapping("/get-all/{mapID}")
  public ResponseEntity<List<FileGetDto>> getAllByMapID(
      @PathVariable UUID mapID,
      @AuthenticationPrincipal User user
  ) {
    List<FileGetDto> list = new ArrayList<>();

    for (File file : fileService.getAllByMapID(mapID)) {
      list.add(FileGetDto.from(file));
    }

    return ResponseEntity.ok(list);
  }

  @GetMapping("get-all")
  public ResponseEntity<List<FileGetDto>> getAll(
      @AuthenticationPrincipal User user
  ) {
    List<FileGetDto> list = new ArrayList<>();

    for (File file : fileService.getAll(user)) {
      list.add(FileGetDto.from(file));
    }

    return ResponseEntity.ok(list);
  }
}
