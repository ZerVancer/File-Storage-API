package com.leo.file_storage_api.services.mapService;

import com.leo.file_storage_api.exceptions.mapExceptions.MapNotFoundException;
import com.leo.file_storage_api.models.map.Map;
import com.leo.file_storage_api.models.user.User;
import com.leo.file_storage_api.repositories.mapRepository.MapRepository;
import com.leo.file_storage_api.services.userService.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class MapService {
  private MapRepository mapRepository;

  private UserService userService;

  public Map createMap(UUID userID, String name) {
    User user = userService.getUser(userID);

    return mapRepository.save(new Map(name, user));
  }

  public List<Map> getAll() {
    return mapRepository.findAll();
  }

  public Map getMap(UUID mapID) {
    return mapRepository.findByMapID(mapID).orElseThrow(MapNotFoundException::new);
  }

  public Map findMapByUserIDAndLocation(UUID userID, String location) {
    return mapRepository.findByUser_UserIDAndLocation(userID, location).orElse(null);
  }

  public boolean mapBelongsToUser(Map map, User user) {
    return map.getUser().equals(user);
  }
}
