package com.pms.farm_service.dto;

public record ChickMortalityRequest(
        Integer mortality_id,
        String placement_code,
        Integer mortality_quantity,
        String description
) {
}
