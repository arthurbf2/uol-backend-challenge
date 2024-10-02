package com.arthurbf.uol_backend.controllers;

import com.arthurbf.uol_backend.dtos.PlayerDTO;
import com.arthurbf.uol_backend.services.PlayerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PlayerController {
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping("/players")
    public ResponseEntity<PlayerDTO> createPlayer(@RequestBody PlayerDTO playerDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(playerService.createPlayer(playerDTO));
    }
}
