package com.greg.matchmaking_service.domain.service;

import com.greg.matchmaking_service.domain.entity.MatchRequest;
import com.greg.matchmaking_service.domain.entity.Player;
import com.greg.matchmaking_service.domain.entity.enums.MatchStatus;
import com.greg.matchmaking_service.domain.repository.MatchRequestRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MatchRequestService {
    private MatchRequestRepository repository;

    public MatchRequestService(MatchRequestRepository repository) {
        this.repository = repository;
    }

    public MatchRequest join(Player player) {
        MatchRequest openRequest = repository.findFirstByStatusAndPlayersCountLessThan(
                MatchStatus.WAITING, 2
        );

        if (openRequest != null) {
            openRequest.getPlayers().add(player);
            return repository.save(openRequest);
        }

        MatchRequest newRequest = new MatchRequest();
        newRequest.setPlayers(new ArrayList<>(List.of(player)));
        newRequest.setStatus(MatchStatus.WAITING);
        return repository.save(newRequest);
    }
}
