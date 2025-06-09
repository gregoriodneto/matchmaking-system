package com.greg.matchmaking_service.domain.service;

import com.greg.matchmaking_service.domain.entity.MatchRequest;
import com.greg.matchmaking_service.domain.entity.Player;
import com.greg.matchmaking_service.domain.entity.enums.MatchStatus;
import com.greg.matchmaking_service.domain.entity.enums.PlayerStatus;
import com.greg.matchmaking_service.domain.repository.MatchRequestRepository;
import com.greg.matchmaking_service.domain.repository.PlayerRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MatchRequestService {
    private MatchRequestRepository repository;
    private MatchService matchService;
    private PlayerService playerService;

    public MatchRequestService(
            MatchRequestRepository repository,
            MatchService matchService,
            PlayerService playerService
    ) {
        this.repository = repository;
        this.playerService = playerService;
        this.matchService = matchService;
    }

    @Transactional
    public MatchRequest join(Player player) {
        MatchRequest openRequest = repository.findFirstByStatusAndPlayersCountLessThan(
                MatchStatus.WAITING, 2
        );

        playerService.setStatus(player, PlayerStatus.WAITING);

        if (openRequest != null) {
            openRequest.getPlayers().add(player);
            MatchRequest updatedResquest = repository.save(openRequest);

            if (updatedResquest.getPlayers().size() == 2) {
                matchService.createMatchFromRequest(updatedResquest);
            }

            return updatedResquest;
        }

        MatchRequest newRequest = new MatchRequest();
        newRequest.setPlayers(new ArrayList<>(List.of(player)));
        newRequest.setStatus(MatchStatus.WAITING);
        return repository.save(newRequest);
    }
}
