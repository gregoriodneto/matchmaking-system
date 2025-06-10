package com.greg.stats_service.controller.dto;

import lombok.Data;

@Data
public class MatchCreatedEventDTO {
    private Long matchId;
    private Long player1Id;
    private Long player2Id;
    private String status;

    public MatchCreatedEventDTO(Long matchId, Long player1Id, Long player2Id, String status) {
        this.matchId = matchId;
        this.player1Id = player1Id;
        this.player2Id = player2Id;
        this.status = status;
    }
}
