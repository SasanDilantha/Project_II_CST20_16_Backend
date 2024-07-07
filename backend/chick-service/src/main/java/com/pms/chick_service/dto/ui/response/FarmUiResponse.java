package com.pms.chick_service.dto.ui.response;

public record FarmUiResponse(
        Integer inventory_id,
        Integer inventory_count,
        Integer available_inventory_count,
        Integer chick_age
) {
}
