package com.pms.feed_service.dto;

public record FarmUiResponse(
        Integer inventory_id,
        double inventory_quantity,
        double available_quantity
) {
}
