package com.pms.farm_service.dto;

public record ChickMortalityRequest(
        Integer mortality_id,
        Integer placement_id,
        Integer mortality_quantity,
        String description
) {
}
