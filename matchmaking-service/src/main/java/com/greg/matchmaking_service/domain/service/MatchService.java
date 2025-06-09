package com.greg.matchmaking_service.domain.service;

import com.greg.matchmaking_service.controller.dtos.MatchCreatedEventDTO;
import com.greg.matchmaking_service.domain.entity.Match;
import com.greg.matchmaking_service.domain.entity.MatchRequest;
import com.greg.matchmaking_service.domain.entity.Player;
import com.greg.matchmaking_service.domain.entity.enums.MatchStatus;
import com.greg.matchmaking_service.domain.entity.enums.PlayerStatus;
import com.greg.matchmaking_service.domain.repository.MatchRepository;
import com.greg.matchmaking_service.domain.repository.MatchRequestRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MatchService {
    private MatchRepository repository;
    private MatchRequestRepository requestRepository;
    private PlayerService playerService;
    private MatchPublisherService matchPublisherService;

    public MatchService(
            MatchRepository repository,
            MatchRequestRepository requestRepository,
            PlayerService playerService,
            MatchPublisherService matchPublisherService
    ) {
        this.repository = repository;
        this.requestRepository = requestRepository;
        this.playerService = playerService;
        this.matchPublisherService = matchPublisherService;
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

        Player player1 = request.getPlayers().get(0);
        Player player2 = request.getPlayers().get(1);
        playerService.setStatus(player1, PlayerStatus.PLAYING);
        playerService.setStatus(player2, PlayerStatus.PLAYING);

        MatchCreatedEventDTO dto = new MatchCreatedEventDTO(
            match.getId(),
            player1.getId(),
            player2.getId(),
            match.getStatus().toString()
        );
        matchPublisherService.sendMatchCreatedEvent(dto);
    }

    public void finishMatch(Match match) {
        setStatus(match, MatchStatus.FINISHED);

        Player player1 = match.getPlayer1();
        playerService.setStatus(player1, PlayerStatus.FINISHED);
        Player player2 = match.getPlayer2();
        playerService.setStatus(player2, PlayerStatus.FINISHED);

        MatchCreatedEventDTO dto = new MatchCreatedEventDTO(
                match.getId(),
                player1.getId(),
                player2.getId(),
                match.getStatus().toString()
        );
        matchPublisherService.sendMatchCreatedEvent(dto);
    }

    public void setStatus(Match match, MatchStatus status) {
        match.setStatus(status);
        repository.save(match);
    }

    public Optional<Match> findById(Long id) {
        return repository.findById(id);
    }

    public List<Match> findAllMatchs() {
        return repository.findAll();
    }
}
