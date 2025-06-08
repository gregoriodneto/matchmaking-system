package com.greg.matchmaking_service.controller;

import com.greg.matchmaking_service.application.MatchmakingApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/matchmaking")
public class MatchmakingController {
    @Autowired private MatchmakingApplicationService service;

    @PostMapping("join/{playerId}")
    @Operation(
            summary = "Fila de matchmaking",
            description = "Um jogador entra na fila de matchmaking."
    )
    public ResponseEntity<?> join(
            @PathVariable Long playerId
    ) {
        try {
            service.join(playerId);
            return ResponseEntity.status(HttpStatus.OK).body("Jogador inserido na fila do Matchmaking");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
