package com.grupp5.file_storage_api.services.mapService;

import com.grupp5.file_storage_api.exceptions.mapExceptions.MapNotFoundException;
import com.grupp5.file_storage_api.models.map.Map;
import com.grupp5.file_storage_api.models.user.User;
import com.grupp5.file_storage_api.repositories.mapRepository.MapRepository;
import com.grupp5.file_storage_api.services.userService.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class MapService {
  private MapRepository mapRepository;

  private UserService userService;

  public void createMap(UUID userID, String location) {
    User user = userService.getUser(userID);

    mapRepository.save(new Map(location, user));
  }

  public void createMap(Map map) {
    mapRepository.save(map);
  }

  public void createEmptyMapDestination(User user) {
    mapRepository.save(new Map(null, user));
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
