package com.greg.matchmaking_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MatchmakingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MatchmakingServiceApplication.class, args);
	}

}
