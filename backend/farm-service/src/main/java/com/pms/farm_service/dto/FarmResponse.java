package com.pms.farm_service.dto;

public record FarmResponse(
        Integer farm_id,
        String farm_name,
        String farm_location,
        String farm_code
) {
}
