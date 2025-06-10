package com.greg.stats_service.domain.service;

import com.greg.stats_service.config.RabbitMQConfig;
import com.greg.stats_service.controller.dto.MatchCreatedEventDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchEventListenerService {
    private CacheService cacheService;

    public MatchEventListenerService(CacheService cacheService) {
        this.cacheService = cacheService;
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void receiveMatchCreated(MatchCreatedEventDTO dto) {
        System.out.println("📥 Mensagem recebida do RabbitMQ: " + dto);
        String key1 = "match:" + dto.getPlayer1Id();
        String key2 = "match:" + dto.getPlayer2Id();
        cacheService.saveRedis(key1, dto);
        cacheService.saveRedis(key2, dto);
        System.out.println("✅ Match salvo no Redis com chave " + key1);
        System.out.println("✅ Match salvo no Redis com chave " + key2);
    }

    public List<Object> findStatsByPlayerId(Long playerId) {
        String key = "match:" + playerId;
        return cacheService.findRedis(key);
    }
}
