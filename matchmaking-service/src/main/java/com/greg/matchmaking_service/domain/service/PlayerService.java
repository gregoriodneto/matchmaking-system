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

    public Player create(String nickname) {
        Player p = new Player();
        p.setNickname(nickname);
        return repository.save(p);
    }

    public boolean existsByNickname(String nickname) {
        return repository.findByNickname(nickname).isPresent();
    }

    public Player findByNickname(String nickname) {
        return repository.findByNickname(nickname)
                .orElseThrow(() -> new RuntimeException("Nickname " + nickname + " já existe."));
    }
}
