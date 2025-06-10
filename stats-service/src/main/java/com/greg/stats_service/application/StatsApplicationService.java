package com.greg.stats_service.application;

import com.greg.stats_service.domain.service.MatchEventListenerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatsApplicationService {
    private MatchEventListenerService matchEventListenerService;

    public StatsApplicationService(MatchEventListenerService matchEventListenerService) {
        this.matchEventListenerService = matchEventListenerService;
    }

    public List<Object> findMatchStats(Long playerId) {
        return matchEventListenerService.findStatsByPlayerId(playerId);
    }
}
