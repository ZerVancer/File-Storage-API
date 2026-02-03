package com.grupp5.file_storage_api.services.fileService;

import com.grupp5.file_storage_api.exceptions.fileExceptions.FileNotFoundException;
import com.grupp5.file_storage_api.exceptions.mapExceptions.IncorrectUserForMapException;
import com.grupp5.file_storage_api.models.file.File;
import com.grupp5.file_storage_api.models.map.Map;
import com.grupp5.file_storage_api.models.user.User;
import com.grupp5.file_storage_api.repositories.fileRepository.FileRepository;
import com.grupp5.file_storage_api.services.mapService.MapService;
import com.grupp5.file_storage_api.services.userService.UserService;
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

  public void saveFile(UUID userID, UUID mapID, String content) {
    Map map;
    User user = userService.getUser(userID);
    if (mapID != null) {
      map = mapService.getMap(mapID);
      if (!mapService.mapBelongsToUser(map, user)) throw new IncorrectUserForMapException();
    } else {
      if ((map = mapService.findMapByUserIDAndLocation(userID, null)) == null){
        map = new Map(null, user);
        mapService.createMap(map);
      }
    }

    File file = new File(map, content);

    fileRepository.save(file);
  }

  public List<File> getAll() {
    return fileRepository.findAll();
  }

  public File getFileByUserID(UUID userID) {
    return fileRepository.findByMap_User_UserID(userID).orElseThrow(FileNotFoundException::new);
  }
}
