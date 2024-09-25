package com.arthurbf.uol_backend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

@Entity
@Table(name = "TB_PLAYERS")
public class PlayerModel {
    private UUID id;

    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;

    private String phone_number;
}
