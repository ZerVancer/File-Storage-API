package com.leo.file_storage_api.repositories.fileRepository;

import com.leo.file_storage_api.models.file.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FileRepository extends JpaRepository<File, UUID> {
  Optional<File> findByFileID(UUID fileID);
  List<File> findAllByMap_MapID(UUID mapMapID);
  List<File> findAllByMap_User_UserID(UUID mapUserUserID);
}
