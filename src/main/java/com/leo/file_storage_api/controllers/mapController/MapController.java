package com.leo.file_storage_api.controllers.mapController;

import com.leo.file_storage_api.dtos.mapDtos.MapRegisteredDto;
import com.leo.file_storage_api.models.map.Map;
import com.leo.file_storage_api.requests.mapRequests.CreateMapRequest;
import com.leo.file_storage_api.services.mapService.MapService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("map")
@AllArgsConstructor
public class MapController {
  private MapService mapService;

  @PostMapping("/{userID}")
  public ResponseEntity<MapRegisteredDto> createMap(
      @PathVariable UUID userID,
      @RequestBody CreateMapRequest request
      ){

    Map map = mapService.createMap(userID, request.name());

    return ResponseEntity.status(HttpStatus.CREATED).body(MapRegisteredDto.from(map));
  }

  @GetMapping
  public ResponseEntity<List<Map>> getAll() {
    return ResponseEntity.ok(mapService.getAll());
  }
}
