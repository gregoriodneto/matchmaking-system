package com.greg.stats_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class StatsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StatsServiceApplication.class, args);
	}

}
