package com.arthurbf.uol_backend.services;

import com.arthurbf.uol_backend.dtos.PlayerDTO;
import com.arthurbf.uol_backend.models.PlayerModel;
import com.arthurbf.uol_backend.repositories.PlayerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final GroupService groupService;

    public PlayerService(PlayerRepository playerRepository, GroupService groupService) {
        this.playerRepository = playerRepository;
        this.groupService = groupService;
    }

    public void savePlayer(PlayerModel player){
        playerRepository.save(player);
    }

    @Transactional
    public PlayerModel createPlayer(PlayerDTO playerDTO) {
        PlayerModel player = new PlayerModel();
        var existingPlayer = playerRepository.findByEmail(playerDTO.email());
        if (existingPlayer.isPresent()){
            throw new RuntimeException("User already exists");
        }

        String codename = null;
        if(playerDTO.group_name() == PlayerModel.GroupName.AVENGERS) {
            codename = groupService.getAvailableAvengerCodename();
        }
        else {
            codename = groupService.getAvailableJusticeLeagueCodename();
        }
        if(codename == null) {
            throw new RuntimeException("No codenames available!");
        }
        BeanUtils.copyProperties(playerDTO, player);
        player.setGroupName(playerDTO.group_name());
        player.setCodename(codename);
        savePlayer(player);
        return player;
    }
}
