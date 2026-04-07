package com.leo.file_storage_api.controllers.fileController;

import com.leo.file_storage_api.dtos.fileDtos.FileDeletedDto;
import com.leo.file_storage_api.dtos.fileDtos.FileDownloadedDto;
import com.leo.file_storage_api.dtos.fileDtos.FileGetDto;
import com.leo.file_storage_api.dtos.fileDtos.FileUploadedDto;
import com.leo.file_storage_api.exceptions.authExceptions.InvalidIdException;
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

    if (correctUser(file, user)) return ResponseEntity.status(HttpStatus.CREATED).body(FileUploadedDto.from(file));
    throw new InvalidIdException();
  }

  @DeleteMapping("/{fileID}")
  public ResponseEntity<FileDeletedDto> deleteFile(
      @PathVariable UUID fileID,
      @AuthenticationPrincipal User user
  ) {
    File file = fileService.deleteFile(fileID);

    if (correctUser(file, user)) return ResponseEntity.status(HttpStatus.ACCEPTED).body(FileDeletedDto.from(file));
    throw new InvalidIdException();
  }

  @GetMapping("/{fileID}")
  public ResponseEntity<FileDownloadedDto> downloadFile(
      @PathVariable UUID fileID,
      @AuthenticationPrincipal User user
  ) {
    File file = fileService.getByFileID(fileID);

    if (correctUser(file, user)) return ResponseEntity.ok(FileDownloadedDto.from(file));
    throw new InvalidIdException();
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

    if (correctUser(fileService.getByFileID(list.getFirst().getFileID()), user)) return ResponseEntity.ok(list);
    throw new InvalidIdException();
  }

  @GetMapping("get-all")
  public ResponseEntity<List<FileGetDto>> getAll(
      @AuthenticationPrincipal User user
  ) {
    List<FileGetDto> list = new ArrayList<>();

    for (File file : fileService.getAll(user)) {
      list.add(FileGetDto.from(file));
    }

    if (correctUser(fileService.getByFileID(list.getFirst().getFileID()), user)) return ResponseEntity.ok(list);
    throw new InvalidIdException();
  }

  private boolean correctUser(File file, User user) {
    return file.getMap().getUser().getUserID().equals(user.getUserID());
  }
}
