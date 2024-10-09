package com.pms.monitoring_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MonitoringServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MonitoringServiceApplication.class, args);
	}

}
