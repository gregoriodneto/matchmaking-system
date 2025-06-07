package com.greg.matchmaking_service.domain.entity;

import com.greg.matchmaking_service.domain.entity.enums.PlayerStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "player")
public class Player extends BaseEntity {
    private String nickname;

    @Enumerated(EnumType.STRING)
    private PlayerStatus status = PlayerStatus.FINISHED;
}
