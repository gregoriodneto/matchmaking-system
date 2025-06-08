package com.greg.matchmaking_service.domain.service;

import com.greg.matchmaking_service.domain.entity.Match;
import com.greg.matchmaking_service.domain.entity.MatchRequest;
import com.greg.matchmaking_service.domain.entity.enums.MatchStatus;
import com.greg.matchmaking_service.domain.repository.MatchRepository;
import com.greg.matchmaking_service.domain.repository.MatchRequestRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class MatchService {
    private MatchRepository repository;
    private MatchRequestRepository requestRepository;

    public MatchService(
            MatchRepository repository,
            MatchRequestRepository requestRepository
    ) {
        this.repository = repository;
        this.requestRepository = requestRepository;
    }

    @Transactional
    public void createMatchFromRequest(MatchRequest request) {
        if (request.getPlayers().size() < 2) {
            throw new IllegalStateException("Precisa de pelo menos 2 jogadores para criar uma partida.");
        }

        Match match = new Match();
        match.setPlayer1(request.getPlayers().get(0));
        match.setPlayer2(request.getPlayers().get(1));
        match.setStatus(MatchStatus.CREATED);
        repository.save(match);

        request.setStatus(MatchStatus.COMPLETED);
        requestRepository.save(request);
    }
}
