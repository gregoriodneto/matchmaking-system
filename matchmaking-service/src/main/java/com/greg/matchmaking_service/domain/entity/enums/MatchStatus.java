package com.greg.matchmaking_service.domain.entity.enums;

public enum MatchStatus {
    CREATED("CREATED"),
    PLAYING("PLAYING"),
    FINISHED("FINISHED");

    private final String status;

    MatchStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
