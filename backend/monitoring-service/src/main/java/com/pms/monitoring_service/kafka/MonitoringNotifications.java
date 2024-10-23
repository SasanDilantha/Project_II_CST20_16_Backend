package com.pms.monitoring_service.kafka;

import com.pms.monitoring_service.dto.MonitoringResponse;
import com.pms.monitoring_service.dto.SensorData;

public record MonitoringNotifications(
        String notificationType,
        String notificationMessage,
        MonitoringResponse flockMonitoringData,
        SensorData sensorData,
        String role // role : admin, manager, vet, other(admin & manager) and all
) { }
