package com.leo.file_storage_api.controllers.mapController;

import com.leo.file_storage_api.dtos.mapDtos.MapGetDto;
import com.leo.file_storage_api.dtos.mapDtos.MapRegisteredDto;
import com.leo.file_storage_api.exceptions.authExceptions.InvalidIdException;
import com.leo.file_storage_api.models.map.Map;
import com.leo.file_storage_api.models.user.User;
import com.leo.file_storage_api.requests.mapRequests.CreateMapRequest;
import com.leo.file_storage_api.services.mapService.MapService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("map")
@AllArgsConstructor
public class MapController {
  private MapService mapService;

  @PostMapping("/create")
  public ResponseEntity<MapRegisteredDto> createMap(
      @RequestBody CreateMapRequest request,
      @AuthenticationPrincipal User user
      ){

    Map map = mapService.createMap(user.getUserID(), request.name());

    if (correctUser(map, user)) return ResponseEntity.status(HttpStatus.CREATED).body(MapRegisteredDto.from(map));
    throw new InvalidIdException();
  }

  @GetMapping("/{mapID}")
  public ResponseEntity<MapGetDto> getMapById(
      @PathVariable UUID mapID,
      @AuthenticationPrincipal User user
  ) {
    Map map = mapService.getMap(mapID);

    if (correctUser(map, user)) return ResponseEntity.ok(MapGetDto.from(map));
    throw new InvalidIdException();
  }

  @GetMapping("/get-all")
  public ResponseEntity<List<MapGetDto>> getAll(
      @AuthenticationPrincipal User user
  ) {
    List<MapGetDto> list = new ArrayList<>();

    for (Map map : mapService.getAll(user)) {
      list.add(MapGetDto.from(map));
    }

    if (correctUser(mapService.getMap(list.getFirst().getMapID()), user)) return ResponseEntity.ok(list);
    throw new InvalidIdException();
  }

  private boolean correctUser(Map map, User user) {
    return map.getUser().getUserID().equals(user.getUserID());
  }
}
