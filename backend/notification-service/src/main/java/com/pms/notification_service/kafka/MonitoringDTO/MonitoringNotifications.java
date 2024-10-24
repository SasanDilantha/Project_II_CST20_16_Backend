package com.pms.notification_service.kafka.MonitoringDTO;

public record MonitoringNotifications(
        String notificationType,
        String notificationMessage,
        MonitoringResponse flockMonitoringData,
        SensorData sensorData,
        String role // role : admin, manager, vet, other(admin & manager) and all
) { }
