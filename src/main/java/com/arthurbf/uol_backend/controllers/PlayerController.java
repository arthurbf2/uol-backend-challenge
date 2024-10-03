package com.arthurbf.uol_backend.controllers;

import com.arthurbf.uol_backend.dtos.PlayerDTO;
import com.arthurbf.uol_backend.exceptions.NoAvailableCodenameException;
import com.arthurbf.uol_backend.exceptions.UserAlreadyExistsException;
import com.arthurbf.uol_backend.services.PlayerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@Controller
@RequestMapping("/players")
public class PlayerController {
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping
    public String createPlayer(@ModelAttribute PlayerDTO playerDTO, Model model) {
        try {
            playerService.createPlayer(playerDTO);
        } catch (UserAlreadyExistsException | NoAvailableCodenameException e) {
            model.addAttribute("error", e.getMessage());
            return "playerForm";
        }
        return "redirect:/";
    }

    @PostMapping("/{id}")
    public String deletePlayer(@PathVariable UUID id) {
        playerService.deletePlayer(id);
        return "redirect:/";
    }

    @GetMapping("/form")
    public String showPlayerForm() {
        return "playerForm";
    }
}
