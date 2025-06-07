package com.greg.matchmaking_service.domain.entity;

import com.greg.matchmaking_service.domain.entity.enums.MatchStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "match")
@Data
public class Match extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "player1_id")
    private Player player1;

    @ManyToOne
    @JoinColumn(name = "player2_id")
    private Player player2;

    @Enumerated(EnumType.STRING)
    private MatchStatus status = MatchStatus.CREATED;
}
