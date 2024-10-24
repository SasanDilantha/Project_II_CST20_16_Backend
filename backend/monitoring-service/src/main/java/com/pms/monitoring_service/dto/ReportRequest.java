package com.pms.monitoring_service.dto;

import jakarta.validation.constraints.NotNull;

public record ReportRequest(
        @NotNull(message = "Placement id is required")
        Integer placementId,
        @NotNull(message = "Farm id is required")
        Integer farmId,
        String reportCode,
        String record
) { }
