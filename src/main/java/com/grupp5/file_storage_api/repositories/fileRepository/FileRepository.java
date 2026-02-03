package com.grupp5.file_storage_api.repositories.fileRepository;

import com.grupp5.file_storage_api.models.file.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface FileRepository extends JpaRepository<File, UUID> {
  Optional<File> findByFileID(UUID fileID);
  Optional<File> findByMap_User_UserID(UUID userID);
}
