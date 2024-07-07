package com.pms.chick_service.dto;

public record MortalityRequest(
        Integer mortality_quantity,
        Integer placement_id
) {
}
