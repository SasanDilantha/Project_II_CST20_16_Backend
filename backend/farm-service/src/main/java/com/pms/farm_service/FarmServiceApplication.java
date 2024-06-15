package com.pms.farm_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
@EnableFeignClients
public class FarmServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FarmServiceApplication.class, args);
	}

}
