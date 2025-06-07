package com.greg.matchmaking_service.domain.entity.enums;

public enum PlayerStatus {
    WAITING("WAITING"),
    PLAYING("PLAYING"),
    FINISHED("FINISHED");

    private String status;

    PlayerStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
