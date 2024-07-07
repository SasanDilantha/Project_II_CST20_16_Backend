package com.pms.farm_service.dto;

import jakarta.validation.constraints.NotNull;

public record PlacementRequest(
        @NotNull(message = "Farm id should be included")
        Integer farm_id,
        @NotNull(message = "Number of placement should be included")
        Integer number_of_placement
) {
}
