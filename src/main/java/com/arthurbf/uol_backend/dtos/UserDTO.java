package com.arthurbf.uol_backend.dtos;

import com.arthurbf.uol_backend.models.PlayerModel;

public record UserDTO(String name, String email, String phone_number, PlayerModel.GroupName group, String codename) {
}
