package com.greg.matchmaking_service.application;

import com.greg.matchmaking_service.domain.entity.Match;
import com.greg.matchmaking_service.domain.entity.MatchRequest;
import com.greg.matchmaking_service.domain.entity.Player;
import com.greg.matchmaking_service.domain.service.MatchRequestService;
import com.greg.matchmaking_service.domain.service.MatchService;
import com.greg.matchmaking_service.domain.service.PlayerService;
import org.springframework.stereotype.Service;

@Service
public class MatchmakingApplicationService {
    private MatchRequestService service;
    private PlayerService playerService;
    private MatchService matchService;

    public MatchmakingApplicationService(
            MatchRequestService service,
            PlayerService playerService,
            MatchService matchService
    ) {
        this.service = service;
        this.playerService = playerService;
        this.matchService = matchService;
    }

    public void join(Long playerId) {
        Player player = playerService.findById(playerId)
                .orElseThrow(() -> new RuntimeException("Jogador não cadastrado."));
        MatchRequest request = service.join(player);

        if (request.getPlayers().size() == 2) {
            matchService.createMatchFromRequest(request);
        }
    }

    public void finishMatch(Long matchId) {
        Match match = matchService.findById(matchId)
                .orElseThrow(() -> new RuntimeException("Partida não encontrada."));
        matchService.finishMatch(match);
    }
}
