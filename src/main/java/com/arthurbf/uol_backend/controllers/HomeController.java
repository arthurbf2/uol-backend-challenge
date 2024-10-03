package com.arthurbf.uol_backend.controllers;

import com.arthurbf.uol_backend.dtos.PlayerDTO;
import com.arthurbf.uol_backend.services.PlayerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private final PlayerService playerService;

    public HomeController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/")
    public String showPlayerList(Model model) {
        List<PlayerDTO> players = playerService.getAllPlayers();
        model.addAttribute("players", players);
        return "playerList";
    }
}
