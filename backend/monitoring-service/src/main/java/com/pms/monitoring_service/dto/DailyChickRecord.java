package com.pms.monitoring_service.dto;

import jakarta.validation.constraints.NotNull;

public record DailyChickRecord(
        @NotNull(message = "Placement id is required")
        Integer placementId,
        @NotNull(message = "Weight is required")
        Double weight,
        @NotNull(message = "Feed consumption is required")
        Double feedConsumption
) { }
