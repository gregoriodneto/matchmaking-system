package com.greg.matchmaking_service.domain.entity;

import com.greg.matchmaking_service.domain.entity.enums.MatchStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "match_request")
@Data
public class MatchRequest extends BaseEntity {
    @ManyToMany
    @JoinTable(
            name = "match_request_players",
            joinColumns = @JoinColumn(name = "match_request_id"),
            inverseJoinColumns = @JoinColumn(name = "player_id")
    )
    private List<Player> players;

    @Enumerated(EnumType.STRING)
    private MatchStatus status = MatchStatus.WAITING;
}