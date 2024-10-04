package com.arthurbf.uol_backend.services;

import com.arthurbf.uol_backend.dtos.PlayerDTO;
import com.arthurbf.uol_backend.exceptions.NoAvailableCodenameException;
import com.arthurbf.uol_backend.exceptions.UserAlreadyExistsException;
import com.arthurbf.uol_backend.models.PlayerModel;
import com.arthurbf.uol_backend.repositories.PlayerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final GroupService groupService;

    public PlayerService(PlayerRepository playerRepository, GroupService groupService) {
        this.playerRepository = playerRepository;
        this.groupService = groupService;
    }

    public void deletePlayer(UUID id) {
        Optional<PlayerModel> player = playerRepository.findById(id);
        if (player.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        playerRepository.delete(player.get());
    }

    @Transactional
    public void createPlayer(PlayerDTO playerDTO) {
        PlayerModel player = new PlayerModel();
        var existingPlayer = playerRepository.findByEmail(playerDTO.email());
        if (existingPlayer.isPresent()){
            throw new UserAlreadyExistsException();
        }
        String codename = null;
        if(playerDTO.group_name() == PlayerModel.GroupName.AVENGERS) {
            codename = getAvailableCodename(groupService.getAvengerCodenames());
        }
        else {
            codename = getAvailableCodename(groupService.getJusticeLeagueCodenames());
        }
        if(codename == null) {
            throw new NoAvailableCodenameException();
        }
        BeanUtils.copyProperties(playerDTO, player); // does not transfer the enum for some reason
        player.setGroupName(playerDTO.group_name());
        player.setCodename(codename);
        playerRepository.save(player);
    }

    public String getAvailableCodename(List<String> codenames) {
        for (String codename : codenames) {
            if (playerRepository.findByCodename(codename).isEmpty()) {
                return codename;
            }
        }
        return null;
    }

    public List<PlayerDTO> getAllPlayers() {
        return playerRepository.findAll().stream()
                .map(player -> new PlayerDTO(player.getId(), player.getName(), player.getEmail(),
                        player.getPhone_number(), player.getGroupName(), player.getCodename()))
                .collect(Collectors.toList());
    }

    public void updatePlayer(UUID id, String name, String phone_number) {
        PlayerModel player = playerRepository.findById(id)
                .orElseThrow(RuntimeException::new);
        player.setName(name);
        player.setPhone_number(phone_number);
        playerRepository.save(player);
    }

    public PlayerDTO getPlayer(UUID id) {
        Optional<PlayerModel> player = playerRepository.findById(id);
        if (player.isEmpty()) {
            throw new RuntimeException();
        }
        var playerModel = player.get();
        return new PlayerDTO(playerModel.getId(), playerModel.getName(), playerModel.getEmail(),
                playerModel.getPhone_number(), playerModel.getGroupName(), playerModel.getCodename());
    }
}
