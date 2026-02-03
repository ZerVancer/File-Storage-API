package com.grupp5.file_storage_api.repositories.mapRepository;

import com.grupp5.file_storage_api.models.map.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MapRepository extends JpaRepository<Map, UUID> {
  Optional<Map> findByMapID(UUID mapID);
}
