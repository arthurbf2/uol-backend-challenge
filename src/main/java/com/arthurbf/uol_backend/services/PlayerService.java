package com.arthurbf.uol_backend.services;

import com.arthurbf.uol_backend.dtos.PlayerDTO;
import com.arthurbf.uol_backend.models.PlayerModel;
import com.arthurbf.uol_backend.repositories.PlayerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public PlayerDTO createPlayer(PlayerDTO playerDTO) {
        PlayerModel player = new PlayerModel();
        var existingPlayer = playerRepository.findByEmail(playerDTO.email());
        if (existingPlayer.isPresent()){
            throw new RuntimeException("User already exists");
        }
        String codename = null;
        if(playerDTO.group_name() == PlayerModel.GroupName.AVENGERS) {
            codename = getAvailableCodename(groupService.getAvengerCodenames());
        }
        else {
            codename = getAvailableCodename(groupService.getJusticeLeagueCodenames());
        }
        if(codename == null) {
            throw new RuntimeException("No codenames available!");
        }
        BeanUtils.copyProperties(playerDTO, player);
        player.setGroupName(playerDTO.group_name());
        player.setCodename(codename);
        savePlayer(player);
        return new PlayerDTO(player.getName(), player.getEmail(), player.getPhone_number(), player.getGroupName(), player.getCodename());
    }

    public String getAvailableCodename(List<String> codenames) {
        for (String codename : codenames) {
            if (playerRepository.findByCodename(codename).isEmpty()) {
                return codename;
            }
        }
        return null;
    }
}
