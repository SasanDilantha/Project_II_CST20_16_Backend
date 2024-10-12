package com.pms.notification_service.kafka;

import com.pms.monitoring_service.kafka.MonitoringNotifications;
import com.pms.notification_service.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class Monitoring {
    private final NotificationRepository repository;
    private final DataMapper mapper;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    private String getWebSocketDestination(String role) {
        return switch (role) {
            case "vet" -> "/topic/vet-notifications";
            case "admin" -> "/topic/admin-notifications";
            case "manager" -> "/topic/manager-notifications";
            case "all" -> "/topic/all-notifications";
            case "other" -> "/topic/other-notifications";
            default -> null; // Ignore messages for unknown roles
        };
    }

    // set up the kafka listener for the monitoring topic
    @KafkaListener(topics = "monitoring-topic", groupId = "monitoring-group")
    private void consumeNotification(MonitoringNotifications monitoringNotifications) {
        log.info("Consumed notification from Kafka topic: {}", monitoringNotifications);
        // Forward notifications based on the recipient role
        String destination = getWebSocketDestination(monitoringNotifications.role());
        if (destination != null) {
            messagingTemplate.convertAndSend(destination, monitoringNotifications);
        }
        repository.save(mapper.mapToNotification(monitoringNotifications));

    }
}
