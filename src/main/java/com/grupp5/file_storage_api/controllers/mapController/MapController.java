package com.grupp5.file_storage_api.controllers.mapController;

import com.grupp5.file_storage_api.models.map.Map;
import com.grupp5.file_storage_api.requests.mapRequests.CreateMapRequest;
import com.grupp5.file_storage_api.services.mapService.MapService;
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
  public ResponseEntity<Map> createMap(
      @PathVariable UUID userID,
      @RequestBody CreateMapRequest request
      ){

    mapService.createMap(userID, request.location());

    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @GetMapping
  public ResponseEntity<List<Map>> getAll() {
    return ResponseEntity.ok(mapService.getAll());
  }
}
