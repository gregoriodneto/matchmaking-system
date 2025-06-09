package com.greg.matchmaking_service.domain.service;

import com.greg.matchmaking_service.domain.entity.Match;
import com.greg.matchmaking_service.domain.entity.enums.MatchStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MatchCheckerJobService {
    private MatchService service;

    public MatchCheckerJobService(MatchService service) {
        this.service = service;
    }

    // Executa a cada 3 minutos (minuto % 3 == 0)
    @Scheduled(cron = "0 */3 * * * *")
    public void checkingPlayersPlaying() {
        System.out.println("Verificando as partidas em andamento para finalizar...");
        List<Match> maches = service.findAllMatchs();
        for (Match match : maches) {
            if (
                    match.getStatus() != MatchStatus.FINISHED
                    && match.getCreatedAt().isBefore(LocalDateTime.now().minusMinutes(10))
            ) {
                service.finishMatch(match);
            }
        }
    }
}
