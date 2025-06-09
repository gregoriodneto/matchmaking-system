package com.greg.matchmaking_service.domain.service;

import com.greg.matchmaking_service.config.RabbitMQConfig;
import com.greg.matchmaking_service.controller.dtos.MatchCreatedEventDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class MatchPublisherService {
    private final RabbitTemplate rabbitTemplate;

    public MatchPublisherService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMatchMessage(String message) {
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE_NAME,
                RabbitMQConfig.ROUTING_KEY,
                message
        );
    }

    public void sendMatchCreatedEvent(MatchCreatedEventDTO dto) {
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE_NAME,
                RabbitMQConfig.ROUTING_KEY,
                dto
        );
    }
}
