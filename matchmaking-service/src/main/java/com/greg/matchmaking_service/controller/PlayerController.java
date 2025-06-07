package com.greg.matchmaking_service.controller;

import com.greg.matchmaking_service.domain.repository.PlayerRepository;
import com.greg.matchmaking_service.domain.service.PlayerService;
import com.greg.matchmaking_service.dto.PlayerDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/player")
public class PlayerController {
    @Autowired private PlayerService service;

    @PostMapping
    @Operation(
            summary = "Cadastrar um Jogador",
            description = "Cadastro de jogador"
    )
    public ResponseEntity<?> create(@RequestBody PlayerDto dto) {
        service.create(dto.getNickname());
        return ResponseEntity.status(HttpStatus.CREATED).body("Jogador criado com sucesso!");
    }
}
