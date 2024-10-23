package com.pms.monitoring_service.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationProducer {
    private final KafkaTemplate<String, MonitoringNotifications> kafkaTemplate;

    public void sendNotification(MonitoringNotifications monitoringNotifications) {
        log.info("Sending notification to Kafka topic: {}", monitoringNotifications);
        System.out.println("Sending notification to Kafka topic: ");
        Message<MonitoringNotifications> message = MessageBuilder
                .withPayload(monitoringNotifications)
                .setHeader(KafkaHeaders.TOPIC, "monitoring-topic")
                .build();
        kafkaTemplate.send(message);
    }
}
