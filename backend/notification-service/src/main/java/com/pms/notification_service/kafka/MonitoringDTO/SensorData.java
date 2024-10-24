package com.pms.notification_service.kafka.MonitoringDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SensorData(
        @JsonProperty("_id")
        String id,
        String temperature,
        String humidity,
        String ammoniaLevel,
        String timestamp
) { }
