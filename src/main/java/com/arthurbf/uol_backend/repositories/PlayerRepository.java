package com.arthurbf.uol_backend.repositories;

import com.arthurbf.uol_backend.models.PlayerModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PlayerRepository extends JpaRepository<PlayerModel, UUID> {
    Optional<PlayerModel> findByEmail(String email);
}
