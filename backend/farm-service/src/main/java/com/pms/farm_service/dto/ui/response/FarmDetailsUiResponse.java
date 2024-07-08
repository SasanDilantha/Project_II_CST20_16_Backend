package com.pms.farm_service.dto.ui.response;

public record FarmDetailsUiResponse(
        Integer farm_id,
        String farm_name,
        String location,
        Integer inventory_id,
        Integer begin_inventory_count,
        Integer available_inventory_count,
        Integer chick_age
) {
}
