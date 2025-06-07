package com.greg.matchmaking_service.domain.service;

import com.greg.matchmaking_service.domain.entity.Player;
import com.greg.matchmaking_service.domain.repository.PlayerRepository;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {
    public PlayerRepository repository;

    public PlayerService(PlayerRepository repository) {
        this.repository = repository;
    }

    public void create(String nickname) {
        Player p = new Player();
        p.setNickname(nickname);
        repository.save(p);
    }
}
