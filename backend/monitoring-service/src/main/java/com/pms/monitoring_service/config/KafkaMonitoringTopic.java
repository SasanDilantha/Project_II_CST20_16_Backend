package com.pms.monitoring_service.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaMonitoringTopic {
    @Bean
    public NewTopic monitoringTopic() {
        return TopicBuilder.name("monitoring-topic")
                .partitions(1)
                .replicas(1)
                .build();
    }
}
