package com.pms.chick_service.dto.ui.response;

public record BlockDetails(
        Integer placement_id,
        String placement_code,
        Integer current_count,
        Integer mortality_count,
        String description
) {
}
