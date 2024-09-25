package com.arthurbf.uol_backend.controllers;

import com.arthurbf.uol_backend.services.PlayerService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlayerController {
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }
}
