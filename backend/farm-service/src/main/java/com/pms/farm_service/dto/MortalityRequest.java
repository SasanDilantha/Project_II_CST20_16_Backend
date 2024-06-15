package com.pms.farm_service.dto;

public record MortalityRequest(
        Integer mortality_quantity,
        Integer placement_id
) {
}
