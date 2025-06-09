package com.greg.matchmaking_service.domain.repository;

import com.greg.matchmaking_service.domain.entity.MatchRequest;
import com.greg.matchmaking_service.domain.entity.enums.MatchStatus;
import io.lettuce.core.dynamic.annotation.Param;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchRequestRepository extends JpaRepository<MatchRequest, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT mr FROM MatchRequest mr WHERE mr.status = :status AND SIZE(mr.players) < :maxPlayers")
    MatchRequest findFirstByStatusAndPlayersCountLessThan(
            @Param("status") MatchStatus status,
            @Param("maxPlayers") int maxPlayers
    );
}
