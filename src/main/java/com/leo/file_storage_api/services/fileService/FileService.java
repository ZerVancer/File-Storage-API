package com.leo.file_storage_api.services.fileService;

import com.leo.file_storage_api.exceptions.fileExceptions.FileNotFoundException;
import com.leo.file_storage_api.exceptions.mapExceptions.IncorrectUserForMapException;
import com.leo.file_storage_api.models.file.File;
import com.leo.file_storage_api.models.map.Map;
import com.leo.file_storage_api.models.user.User;
import com.leo.file_storage_api.repositories.fileRepository.FileRepository;
import com.leo.file_storage_api.services.mapService.MapService;
import com.leo.file_storage_api.services.userService.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class FileService {
  private FileRepository fileRepository;

  private MapService mapService;
  private UserService userService;

  public File saveFile(UUID userID, UUID mapID, String content) {
    Map map;
    User user = userService.getUser(userID);
    if (mapID != null) {
      map = mapService.getMap(mapID);
      if (!mapService.mapBelongsToUser(map, user)) throw new IncorrectUserForMapException();
    } else {
      map = mapService.findMapByUserIDAndLocation(userID, null);
    }

    File file = new File(map, content);

    return fileRepository.save(file);
  }

  public File deleteFile(UUID fileID) {
    File file = fileRepository.findByFileID(fileID).orElse(null);
    fileRepository.deleteById(fileID);

    return file;
  }

  public List<File> getAll() {
    return fileRepository.findAll();
  }

  public File getFileByUserID(UUID userID) {
    return fileRepository.findByMap_User_UserID(userID).orElseThrow(FileNotFoundException::new);
  }
}
