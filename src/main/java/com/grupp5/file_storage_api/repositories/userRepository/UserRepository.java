package com.grupp5.file_storage_api.repositories.userRepository;

import com.grupp5.file_storage_api.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
  Optional<User> findByUserID(UUID userID);
}
