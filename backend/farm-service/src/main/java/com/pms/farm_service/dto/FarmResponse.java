package com.pms.farm_service.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record FarmResponse(
        Integer farm_id,
        String farm_name,
        String farm_location,
        String farm_code
) {
}
