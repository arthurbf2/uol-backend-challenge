package com.arthurbf.uol_backend.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "TB_PLAYERS")
@Getter
@Setter
public class PlayerModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;

    private String phone_number;

    @NotBlank
    private String codename;

    @Enumerated(EnumType.STRING)
    private GroupName groupName;

    public enum GroupName {
        AVENGERS,
        JUSTICE_LEAGUE
    }
}
