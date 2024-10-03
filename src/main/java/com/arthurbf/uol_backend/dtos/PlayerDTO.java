package com.arthurbf.uol_backend.dtos;

import com.arthurbf.uol_backend.models.PlayerModel;

import java.util.UUID;

public record PlayerDTO(UUID id, String name, String email, String phone_number, PlayerModel.GroupName group_name, String codename) {
}
