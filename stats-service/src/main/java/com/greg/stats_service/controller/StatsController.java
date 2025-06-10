package com.greg.stats_service.controller;

import com.greg.stats_service.application.StatsApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/stats")
public class StatsController {
    @Autowired private StatsApplicationService service;

    @GetMapping("/{playerId}")
    @Operation(
            summary = "Busca de estatísticas do jogador",
            description = "Retorna todas as estatísitcas das partidas de um jogador específico."
    )
    public ResponseEntity<?> getStats(@PathVariable Long playerId) {
        try {
            List<Object> stats = service.findMatchStats(playerId);
            if (stats.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        "Nenhuma estatística encontrada para o playerId: " + playerId
                );
            }
            return ResponseEntity.ok(stats);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
