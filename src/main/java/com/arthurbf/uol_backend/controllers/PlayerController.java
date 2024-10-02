package com.arthurbf.uol_backend.controllers;

import com.arthurbf.uol_backend.dtos.PlayerDTO;
import com.arthurbf.uol_backend.services.PlayerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


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

    @GetMapping("/players")
    public ResponseEntity<List<PlayerDTO>> getPlayers(){
        return ResponseEntity.status(HttpStatus.OK).body(playerService.getAllPlayers());
    }

    @PostMapping("/players/{id}")
    public ResponseEntity<String> deletePlayer(@PathVariable UUID id) {
        if(playerService.deletePlayer(id)) {
            return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
    }
}
