package com.pms.monitoring_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SensorData(
        @JsonProperty("_id")
        String id,
        String temperature,
        String humidity,
        String ammoniaLevel,
        String timestamp
) { }
