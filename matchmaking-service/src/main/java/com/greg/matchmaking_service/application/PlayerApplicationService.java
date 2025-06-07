package com.greg.matchmaking_service.application;

import com.greg.matchmaking_service.domain.entity.Player;
import com.greg.matchmaking_service.domain.service.PlayerService;
import org.springframework.stereotype.Service;

@Service
public class PlayerApplicationService {
    private PlayerService service;

    public PlayerApplicationService(PlayerService service) {
        this.service = service;
    }

    public Player create(String nickname) {
        boolean exists = service.existsByNickname(nickname);
        if (exists) {
            throw new IllegalArgumentException("Nickname " + nickname + " já existe.");
        }
        return service.create(nickname);
    }
}
