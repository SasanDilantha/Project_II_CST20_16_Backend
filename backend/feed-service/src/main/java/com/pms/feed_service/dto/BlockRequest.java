package com.pms.feed_service.dto;

public record BlockRequest(
        Integer block_id,
        Integer storage_id,
        String placement_code,
        double block_quantity
) {
}
