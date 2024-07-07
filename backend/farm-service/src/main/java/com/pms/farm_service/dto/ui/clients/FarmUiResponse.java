package com.pms.farm_service.dto.ui.clients;

public record FarmUiResponse(
        Integer inventory_count,
        Integer available_inventory_count,
        Integer chick_age
) {
}
